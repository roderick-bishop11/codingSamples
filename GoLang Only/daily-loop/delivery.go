// Package delivery handles sending the generated report to its destination.
package delivery

import (
	"bytes"
	"fmt"
	"io"
	"log/slog"
	"net/http"
	"os"
	"path/filepath"
	"strings"
	"time"

	"github.com/yourusername/daily-loop/internal/config"
)

// Deliver routes the report to the configured delivery method.
func Deliver(report string, cfg *config.Config, log *slog.Logger) error {
	switch cfg.Delivery.Method {
	case "stdout":
		return toStdout(report)
	case "file":
		return toFile(report, cfg.Delivery.File, log)
	case "webhook":
		return toWebhook(report, cfg.Delivery.Webhook, log)
	case "ntfy":
		return toNtfy(report, cfg.Delivery.Ntfy, log)
	default:
		return fmt.Errorf("unknown delivery method: %s", cfg.Delivery.Method)
	}
}

// ----- Stdout -----

func toStdout(report string) error {
	_, err := fmt.Println(report)
	return err
}

// ----- File -----

func toFile(report string, cfg config.FileDelivery, log *slog.Logger) error {
	path := cfg.Path
	if path == "" {
		path = fmt.Sprintf("daily-report-%s.md", time.Now().Format("2006-01-02"))
	}

	if err := os.MkdirAll(filepath.Dir(path), 0o755); err != nil {
		return fmt.Errorf("mkdir: %w", err)
	}

	if err := os.WriteFile(path, []byte(report), 0o644); err != nil {
		return fmt.Errorf("write file: %w", err)
	}

	log.Info("report written", "path", path)
	return nil
}

// ----- Webhook (Home Assistant compatible) -----

func toWebhook(report string, cfg config.WebhookDelivery, log *slog.Logger) error {
	if cfg.URL == "" {
		return fmt.Errorf("delivery.webhook.url is required")
	}

	// Wrap in a JSON payload that Home Assistant webhooks expect.
	payload := fmt.Sprintf(`{"report":%s}`, jsonStringEscape(report))

	req, err := http.NewRequest(http.MethodPost, cfg.URL, strings.NewReader(payload))
	if err != nil {
		return fmt.Errorf("build webhook request: %w", err)
	}
	req.Header.Set("Content-Type", "application/json")
	for k, v := range cfg.Headers {
		req.Header.Set(k, v)
	}

	return doHTTP(req, log, "webhook")
}

// ----- ntfy push notification -----

func toNtfy(report string, cfg config.NtfyDelivery, log *slog.Logger) error {
	if cfg.URL == "" {
		return fmt.Errorf("delivery.ntfy.url is required")
	}

	// ntfy supports markdown in the body; keep report as-is.
	// Title is the first non-empty line of the report.
	title := extractTitle(report)

	req, err := http.NewRequest(http.MethodPost, cfg.URL, bytes.NewReader([]byte(report)))
	if err != nil {
		return fmt.Errorf("build ntfy request: %w", err)
	}
	req.Header.Set("Content-Type", "text/markdown")
	req.Header.Set("Title", title)
	if cfg.Priority != "" {
		req.Header.Set("Priority", cfg.Priority)
	}
	if cfg.Token != "" {
		req.Header.Set("Authorization", "Bearer "+cfg.Token)
	}

	return doHTTP(req, log, "ntfy")
}

// ----- Shared HTTP helper -----

func doHTTP(req *http.Request, log *slog.Logger, method string) error {
	client := &http.Client{Timeout: 15 * time.Second}
	resp, err := client.Do(req)
	if err != nil {
		return fmt.Errorf("%s request: %w", method, err)
	}
	defer resp.Body.Close()

	body, _ := io.ReadAll(resp.Body)
	if resp.StatusCode >= 400 {
		return fmt.Errorf("%s delivery failed: HTTP %d — %s", method, resp.StatusCode, body)
	}

	log.Info("delivery successful", "method", method, "status", resp.StatusCode)
	return nil
}

// ----- Utilities -----

func extractTitle(report string) string {
	for _, line := range strings.SplitN(report, "\n", 10) {
		line = strings.TrimSpace(line)
		line = strings.TrimLeft(line, "#🗓 ")
		if line != "" {
			return line
		}
	}
	return "Daily Brief"
}

// jsonStringEscape wraps s in a JSON string, escaping as needed.
func jsonStringEscape(s string) string {
	b, _ := marshalString(s)
	return string(b)
}

func marshalString(s string) ([]byte, error) {
	var buf bytes.Buffer
	buf.WriteByte('"')
	for _, r := range s {
		switch r {
		case '"':
			buf.WriteString(`\"`)
		case '\\':
			buf.WriteString(`\\`)
		case '\n':
			buf.WriteString(`\n`)
		case '\r':
			buf.WriteString(`\r`)
		case '\t':
			buf.WriteString(`\t`)
		default:
			buf.WriteRune(r)
		}
	}
	buf.WriteByte('"')
	return buf.Bytes(), nil
}
