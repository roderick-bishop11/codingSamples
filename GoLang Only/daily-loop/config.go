package config

import (
	"fmt"
	"os"
	"strings"

	"gopkg.in/yaml.v3"
)

// Config is the root configuration for daily-loop.
type Config struct {
	Schedule ScheduleConfig  `yaml:"schedule"`
	Anthropic AnthropicConfig `yaml:"anthropic"`
	User     UserConfig      `yaml:"user"`
	Delivery DeliveryConfig  `yaml:"delivery"`
}

type ScheduleConfig struct {
	// Cron expression, e.g. "0 7 * * *" for 7am daily.
	Cron     string `yaml:"cron"`
	Timezone string `yaml:"timezone"`
}

type AnthropicConfig struct {
	// APIKey is read from ANTHROPIC_API_KEY env var if not set here.
	APIKey    string `yaml:"api_key"`
	Model     string `yaml:"model"`
	MaxTokens int    `yaml:"max_tokens"`
	SkillPath string `yaml:"skill_path"`
}

type UserConfig struct {
	Name     string `yaml:"name"`
	Location string `yaml:"location"`
	WorkWeekLocation string `yaml:"work_week_location"`
	// Context is freeform text appended to the user message — calendar snippets,
	// standing interests, notes, etc.
	Context string `yaml:"context"`
}

//todo: simplify delivery
type DeliveryConfig struct {
	// Method: stdout | file | webhook | ntfy
	Method string `yaml:"method"`
	File    FileDelivery    `yaml:"file"`
	// Webhook WebhookDelivery `yaml:"webhook"`
	// Ntfy    NtfyDelivery    `yaml:"ntfy"`
}

type FileDelivery struct {
	Path string `yaml:"path"`
}

// type WebhookDelivery struct {
// 	URL     string            `yaml:"url"`
// 	Headers map[string]string `yaml:"headers"`
// }

// type NtfyDelivery struct {
// 	URL      string `yaml:"url"`      // e.g. https://ntfy.sh/my-topic
// 	Priority string `yaml:"priority"` // default | high | urgent
// 	Token    string `yaml:"token"`    // optional auth token
// }

// Load reads config from the given YAML file path, then overlays
// environment variable overrides.
func Load(path string) (*Config, error) {
	cfg := defaults()

	f, err := os.Open(path)
	if err != nil {
		return nil, fmt.Errorf("open config: %w", err)
	}
	defer f.Close()

	if err := yaml.NewDecoder(f).Decode(cfg); err != nil {
		return nil, fmt.Errorf("decode config: %w", err)
	}

	applyEnvOverrides(cfg)

	if err := validate(cfg); err != nil {
		return nil, fmt.Errorf("invalid config: %w", err)
	}

	return cfg, nil
}

func defaults() *Config {
	return &Config{
		Schedule: ScheduleConfig{
			Cron:     "0 7 * * *",
			Timezone: "Local",
		},
		Anthropic: AnthropicConfig{
			Model:     "claude-sonnet-4-20250514",
			MaxTokens: 2048,
			SkillPath: "skills/daily-report.md",
		},
		Delivery: DeliveryConfig{
			Method: "stdout",
			Ntfy: NtfyDelivery{
				Priority: "default",
			},
		},
	}
}

// applyEnvOverrides allows secrets to live in env rather than config files.
func applyEnvOverrides(cfg *Config) {
	if v := os.Getenv("ANTHROPIC_API_KEY"); v != "" {
		cfg.Anthropic.APIKey = v
	}
	if v := os.Getenv("DAILY_LOOP_NTFY_TOKEN"); v != "" {
		cfg.Delivery.Ntfy.Token = v
	}
	if v := os.Getenv("DAILY_LOOP_HA_TOKEN"); v != "" {
		// Expand ${HA_TOKEN} placeholder in webhook headers.
		for k, hv := range cfg.Delivery.Webhook.Headers {
			cfg.Delivery.Webhook.Headers[k] = strings.ReplaceAll(hv, "${HA_TOKEN}", v)
		}
	}
}

func validate(cfg *Config) error {
	if cfg.Anthropic.APIKey == "" {
		return fmt.Errorf("anthropic.api_key is required (or set ANTHROPIC_API_KEY)")
	}
	if cfg.Anthropic.SkillPath == "" {
		return fmt.Errorf("anthropic.skill_path must point to the skill markdown file")
	}
	switch cfg.Delivery.Method {
	case "stdout", "file", "webhook", "ntfy":
		// valid
	default:
		return fmt.Errorf("delivery.method must be one of: stdout, file, webhook, ntfy")
	}
	return nil
}
