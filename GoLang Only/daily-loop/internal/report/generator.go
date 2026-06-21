// Package report handles loading the skill and generating the daily report.
package report

import (
	"context"
	"fmt"
	"log/slog"
	"os"
	"strings"
	"time"

	"github.com/yourusername/daily-loop/internal/config"
)

// LLMClient is the interface the Generator uses to call the language model.
// The real *anthropic.Client satisfies this; tests can inject a fake.
type LLMClient interface {
	RunWithWebSearch(ctx context.Context, systemPrompt, userMsg string) (string, error)
}

// Generator produces the daily report by calling the LLM with the skill.
type Generator struct {
	client LLMClient
	cfg    *config.Config
	log    *slog.Logger
}

func New(client LLMClient, cfg *config.Config, log *slog.Logger) *Generator {
	return &Generator{client: client, cfg: cfg, log: log}
}

// Generate loads the skill, constructs the user message, and runs the LLM.
func (g *Generator) Generate(ctx context.Context) (string, error) {
	skill, err := loadSkill(g.cfg.Anthropic.SkillPath)
	if err != nil {
		return "", fmt.Errorf("load skill: %w", err)
	}

	userMsg := buildUserMessage(g.cfg.User)

	g.log.Info("generating report", "user", g.cfg.User.Name, "location", g.cfg.User.Location)

	report, err := g.client.RunWithWebSearch(ctx, skill, userMsg)
	if err != nil {
		return "", fmt.Errorf("LLM call: %w", err)
	}

	return report, nil
}

// loadSkill reads the skill markdown from the given path.
func loadSkill(path string) (string, error) {
	data, err := os.ReadFile(path)
	if err != nil {
		return "", fmt.Errorf("read %s: %w", path, err)
	}
	return strings.TrimSpace(string(data)), nil
}

// buildUserMessage constructs the user-facing prompt from config context,
// injecting the current date and user profile so the LLM can personalize.
func buildUserMessage(u config.UserConfig) string {
	now := time.Now()
	timezone := now.Location().String()

	var sb strings.Builder

	sb.WriteString(fmt.Sprintf("Today is %s (%s).\n\n",
		now.Format("Monday, January 2, 2006 — 3:04 PM"),
		timezone,
	))

	if u.Name != "" {
		sb.WriteString(fmt.Sprintf("User: %s\n", u.Name))
	}
	if u.Location != "" {
		sb.WriteString(fmt.Sprintf("Location: %s\n", u.Location))
	}

	if u.Context != "" {
		sb.WriteString("\n--- User Context ---\n")
		sb.WriteString(strings.TrimSpace(u.Context))
		sb.WriteString("\n--- End Context ---\n")
	}

	//this is the prompt,
	sb.WriteString("\nPlease generate my probabilistic daily brief now. Search for current signals first, then synthesize the report following the skill instructions exactly.")

	return sb.String()
}
