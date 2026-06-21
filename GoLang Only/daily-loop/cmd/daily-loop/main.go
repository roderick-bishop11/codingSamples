package main

import (
	"context"
	"flag"
	"log/slog"
	"os"
	"time"

	"github.com/yourusername/daily-loop/internal/anthropic"
	"github.com/yourusername/daily-loop/internal/config"
	"github.com/yourusername/daily-loop/internal/delivery"
	"github.com/yourusername/daily-loop/internal/report"
)

func main() {
	cfgPath := flag.String("config", "config.yaml", "Path to the YAML configuration file")
	logLevel := flag.String("log-level", "info", "Log level: debug, info, warn, error")
	dryRun := flag.Bool("dry-run", false, "Generate the report but skip delivery")
	flag.Parse()

	handler := slog.NewTextHandler(os.Stdout, &slog.HandlerOptions{Level: parseLevel(*logLevel)})
	logger := slog.New(handler)

	cfg, err := config.Load(*cfgPath)
	if err != nil {
		logger.Error("failed to load config", "err", err)
		os.Exit(1)
	}

	client := anthropic.New(
		cfg.Anthropic.APIKey,
		cfg.Anthropic.Model,
		cfg.Anthropic.MaxTokens,
		logger,
	)
	generator := report.New(client, cfg, logger)

	ctx, cancel := context.WithTimeout(context.Background(), 2*time.Minute)
	defer cancel()

	reportText, err := generator.Generate(ctx)
	if err != nil {
		logger.Error("failed to generate report", "err", err)
		os.Exit(1)
	}

	if *dryRun {
		os.Stdout.WriteString(reportText + "\n")
		return
	}

	if err := delivery.Deliver(reportText, cfg, logger); err != nil {
		logger.Error("failed to deliver report", "err", err)
		os.Exit(1)
	}

	logger.Info("daily report delivered", "method", cfg.Delivery.Method)
}

func parseLevel(level string) slog.Level {
	switch level {
	case "debug":
		return slog.LevelDebug
	case "warn":
		return slog.LevelWarn
	case "error":
		return slog.LevelError
	default:
		return slog.LevelInfo
	}
}
