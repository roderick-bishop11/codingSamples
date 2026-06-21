// Package report_test tests the report generator using a fake LLM client.
// It lives in test/ and uses Go's external test package convention (_test suffix).
package report_test

import (
	"context"
	"io"
	"log/slog"
	"os"
	"path/filepath"
	"strings"
	"testing"

	report "github.com/yourusername/daily-loop/src"
	"github.com/yourusername/daily-loop/internal/config"
)

// --- GIVEN: a fake LLM client that returns a fixed report string ---

// fakeLLMClient satisfies the report.LLMClient interface without hitting
// the network. This is Go's idiomatic way to mock: implement the interface.
type fakeLLMClient struct {
	returnReport string
	returnErr    error
}

func (f *fakeLLMClient) RunWithWebSearch(_ context.Context, _, _ string) (string, error) {
	return f.returnReport, f.returnErr
}

// --- Test ---

func TestGenerate(t *testing.T) {
	// GIVEN: a skill file on disk (Generate reads it via loadSkill internally)
	skillDir := t.TempDir()                        // Go creates and cleans up a temp dir per test
	skillPath := filepath.Join(skillDir, "skill.md")
	err := os.WriteFile(skillPath, []byte("You are a daily-brief assistant."), 0644)
	if err != nil {
		t.Fatalf("setup: could not write skill file: %v", err)
	}

	// GIVEN: a minimal config pointing at the fake skill file
	cfg := &config.Config{
		Anthropic: config.AnthropicConfig{SkillPath: skillPath},
		User:      config.UserConfig{Name: "Roberto", Location: "NYC"},
	}

	// GIVEN: a fake client that will return a predictable report
	wantReport := "# Daily Brief\nEverything looks good today."
	fakeClient := &fakeLLMClient{returnReport: wantReport}

	// GIVEN: a silent logger so test output stays clean
	logger := slog.New(slog.NewTextHandler(io.Discard, nil))

	// WHEN: Generate is called
	g := report.New(fakeClient, cfg, logger)
	got, err := g.Generate(context.Background())

	// THEN: no error is returned
	if err != nil {
		t.Fatalf("Generate() returned unexpected error: %v", err)
	}

	// THEN: the report returned by the LLM is passed through unchanged
	if !strings.Contains(got, "Daily Brief") {
		t.Errorf("Generate() = %q, want string containing %q", got, "Daily Brief")
	}
}
