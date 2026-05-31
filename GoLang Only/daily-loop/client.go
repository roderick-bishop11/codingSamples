// Package anthropic provides a minimal client for the Anthropic Messages API
// with support for server-side tool use (web search) via an agentic loop.
package anthropic

import (
	"bytes"
	"context"
	"encoding/json"
	"fmt"
	"io"
	"log/slog"
	"net/http"
	"time"
)

const (
	baseURL       = "https://api.anthropic.com/v1/messages"
	anthropicVer  = "2023-06-01"
	maxToolRounds = 8 // prevent infinite tool loops
)

// Client is a minimal Anthropic Messages API client.
type Client struct {
	apiKey     string
	model      string
	maxTokens  int
	httpClient *http.Client
	log        *slog.Logger
}

func New(apiKey, model string, maxTokens int, log *slog.Logger) *Client {
	return &Client{
		apiKey:    apiKey,
		model:     model,
		maxTokens: maxTokens,
		httpClient: &http.Client{
			Timeout: 120 * time.Second,
		},
		log: log,
	}
}

// ----- Request / Response types -----

type Message struct {
	Role    string    `json:"role"`
	Content []Content `json:"content"`
}

type Content struct {
	Type       string      `json:"type"`
	Text       string      `json:"text,omitempty"`
	ID         string      `json:"id,omitempty"`         // tool_use id
	Name       string      `json:"name,omitempty"`       // tool name
	Input      interface{} `json:"input,omitempty"`      // tool input
	ToolUseID  string      `json:"tool_use_id,omitempty"`// tool_result ref
	IsError    bool        `json:"is_error,omitempty"`
	// Content field for tool_result can be string or []Content
	ToolResult interface{} `json:"-"`
}

// MarshalJSON handles the polymorphic content field for tool_result.
func (c Content) MarshalJSON() ([]byte, error) {
	type Alias Content
	if c.Type == "tool_result" {
		return json.Marshal(struct {
			Type      string `json:"type"`
			ToolUseID string `json:"tool_use_id"`
			Content   string `json:"content"`
		}{
			Type:      c.Type,
			ToolUseID: c.ToolUseID,
			Content:   c.Text,
		})
	}
	return json.Marshal(Alias(c))
}

type request struct {
	Model     string    `json:"model"`
	MaxTokens int       `json:"max_tokens"`
	System    string    `json:"system,omitempty"`
	Messages  []Message `json:"messages"`
	Tools     []Tool    `json:"tools,omitempty"`
}

type Tool struct {
	Type string `json:"type"`
	Name string `json:"name"`
}

type response struct {
	ID         string    `json:"id"`
	StopReason string    `json:"stop_reason"`
	Content    []rawContent `json:"content"`
	Error      *apiError `json:"error,omitempty"`
}

type rawContent struct {
	Type  string          `json:"type"`
	Text  string          `json:"text,omitempty"`
	ID    string          `json:"id,omitempty"`
	Name  string          `json:"name,omitempty"`
	Input json.RawMessage `json:"input,omitempty"`
}

type apiError struct {
	Type    string `json:"type"`
	Message string `json:"message"`
}

// ----- Public API -----

// RunWithWebSearch sends the conversation to the model with web search enabled.
// It runs an agentic loop until the model stops with end_turn (or maxToolRounds
// is exhausted). Returns the full concatenated text response.
func (c *Client) RunWithWebSearch(ctx context.Context, systemPrompt, userMsg string) (string, error) {
	tools := []Tool{
		{Type: "web_search_20250305", Name: "web_search"},
	}

	messages := []Message{
		{Role: "user", Content: []Content{{Type: "text", Text: userMsg}}},
	}

	for round := 0; round < maxToolRounds; round++ {
		c.log.Debug("anthropic request", "round", round, "messages", len(messages))

		resp, err := c.post(ctx, request{
			Model:     c.model,
			MaxTokens: c.maxTokens,
			System:    systemPrompt,
			Messages:  messages,
			Tools:     tools,
		})
		if err != nil {
			return "", err
		}

		// Append assistant turn to history.
		assistantContents := rawToContents(resp.Content)
		messages = append(messages, Message{
			Role:    "assistant",
			Content: assistantContents,
		})

		if resp.StopReason == "end_turn" {
			return extractText(resp.Content), nil
		}

		if resp.StopReason == "tool_use" {
			// Build tool_result turn. For server-side web_search, Anthropic
			// executes the search and embeds results in the response itself —
			// we acknowledge each tool_use with an empty result so the loop
			// can continue if the model still has more to say.
			toolResults := buildToolResults(resp.Content)
			if len(toolResults) == 0 {
				// No tool calls found despite stop_reason — treat as done.
				return extractText(resp.Content), nil
			}
			messages = append(messages, Message{
				Role:    "user",
				Content: toolResults,
			})
			c.log.Debug("tool round complete", "results", len(toolResults))
			continue
		}

		// max_tokens or other stop — return what we have.
		c.log.Warn("unexpected stop_reason", "reason", resp.StopReason)
		return extractText(resp.Content), nil
	}

	return "", fmt.Errorf("agentic loop exceeded %d rounds without end_turn", maxToolRounds)
}

// ----- Helpers -----

func (c *Client) post(ctx context.Context, req request) (*response, error) {
	body, err := json.Marshal(req)
	if err != nil {
		return nil, fmt.Errorf("marshal request: %w", err)
	}

	httpReq, err := http.NewRequestWithContext(ctx, http.MethodPost, baseURL, bytes.NewReader(body))
	if err != nil {
		return nil, fmt.Errorf("build request: %w", err)
	}
	httpReq.Header.Set("Content-Type", "application/json")
	httpReq.Header.Set("x-api-key", c.apiKey)
	httpReq.Header.Set("anthropic-version", anthropicVer)

	var resp *http.Response
	var lastErr error
	for attempt := 1; attempt <= 3; attempt++ {
		resp, err = c.httpClient.Do(httpReq)
		if err == nil {
			break
		}
		lastErr = err
		c.log.Warn("http attempt failed", "attempt", attempt, "err", err)
		select {
		case <-ctx.Done():
			return nil, ctx.Err()
		case <-time.After(time.Duration(attempt*attempt) * time.Second):
		}
		// Reset body for retry.
		httpReq.Body = io.NopCloser(bytes.NewReader(body))
	}
	if resp == nil {
		return nil, fmt.Errorf("all retries failed: %w", lastErr)
	}
	defer resp.Body.Close()

	raw, err := io.ReadAll(resp.Body)
	if err != nil {
		return nil, fmt.Errorf("read response: %w", err)
	}

	var apiResp response
	if err := json.Unmarshal(raw, &apiResp); err != nil {
		return nil, fmt.Errorf("decode response (status %d): %w\nbody: %s", resp.StatusCode, err, raw)
	}

	if resp.StatusCode >= 400 {
		msg := fmt.Sprintf("API error %d", resp.StatusCode)
		if apiResp.Error != nil {
			msg = fmt.Sprintf("API error %d: %s — %s", resp.StatusCode, apiResp.Error.Type, apiResp.Error.Message)
		}
		return nil, fmt.Errorf("%s", msg)
	}

	return &apiResp, nil
}

func extractText(contents []rawContent) string {
	var sb bytes.Buffer
	for _, c := range contents {
		if c.Type == "text" && c.Text != "" {
			if sb.Len() > 0 {
				sb.WriteString("\n")
			}
			sb.WriteString(c.Text)
		}
	}
	return sb.String()
}

func rawToContents(raw []rawContent) []Content {
	out := make([]Content, 0, len(raw))
	for _, r := range raw {
		c := Content{
			Type:  r.Type,
			Text:  r.Text,
			ID:    r.ID,
			Name:  r.Name,
			Input: r.Input,
		}
		out = append(out, c)
	}
	return out
}

func buildToolResults(contents []rawContent) []Content {
	var results []Content
	for _, c := range contents {
		if c.Type != "tool_use" {
			continue
		}
		results = append(results, Content{
			Type:      "tool_result",
			ToolUseID: c.ID,
			// For server-side web_search, results are embedded by Anthropic —
			// we just acknowledge. For client-side tools you'd fill Text here.
			Text: "",
		})
	}
	return results
}
