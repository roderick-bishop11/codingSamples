---
name: codebase-speedrun
description: Analyze an unfamiliar software repository and create a human-readable HTML architecture tour plus a concise study guide. Use when a user needs to get oriented in a codebase, understand its language idioms, data structures, build and dependency model, service and transport layers, tests, and the most important code paths to study. Detects and adapts to monorepos with multiple apps/services sharing common libraries.
---

# Codebase Speedrun

Produce a grounded, beginner-friendly orientation/speedrun doc for an unfamiliar repository. Act as a senior engineer on the team. Explain what the code demonstrates, how the pieces connect, and where uncertainty remains. Ground business context in repository evidence and label the rest as unknown.

## Inputs and Outputs

Use the current repository unless the user supplies another path. Ask for a project name or deployment context only when it is genuinely needed; otherwise infer a neutral title from the repository.

Create an output directory named `codebase-speedrun/` at the repository root unless the user specifies another location. Doing this in a way that's repeatable and prevents overwrites on valuable study files. 

Write:

1. `codebase-speedrun/{Project Name/App name}.html`: a self-contained, responsive HTML SPA with inline CSS and JavaScript. The document title and visible heading must use an inferred project name, such as `[Project Name/App Name] Codebase Speedrun Guide`, rather than the example filename. It must open directly from disk without a server or external assets.
2. `codebase-speedrun/{project name}-study-guide.md`: a simple bullet-point study guide.

If either file exists, add a dated suffix or ask the user before replacing it.

## Repository Reconnaissance

Explore in this order, using targeted file discovery and reads rather than dumping the whole tree:

1. Establish scope: top-level files, source roots, test roots, generated/vendor/build directories, README and contribution docs. **Determine whether the repository is a monorepo** — multiple independently-deployable modules (apps/services/packages) sharing common libraries, a single workspace/settings manifest (e.g. `settings.gradle.kts`, `pnpm-workspace.yaml`, `lerna.json`, `nx.json`, Go/Cargo workspaces, a top-level `apps/`+`libs/`/`packages/` split), and/or one CI pipeline deploying several artifacts. If so, follow the Monorepo Handling steps below in addition to the rest of this reconnaissance.
2. Identify the language and framework from source extensions, build manifests, lockfiles, wrapper files, and CI/container/deployment files.
3. Read the primary build and dependency manifests, application entry points, configuration examples, and deployment descriptors. Refer to credentials, secrets, tokens, private keys, and sensitive configuration by name only; their values stay out of every output.
4. Map source packages/modules by responsibility. Locate routes/controllers, services/use cases, gateways/clients, persistence, messaging, configuration, models, utilities, and error handling.
5. Inspect representative files in each area and trace two or three important flows end to end from transport/event entry point through business logic to external dependency or response.
6. Inspect tests, fixtures, mocks, test utilities, and CI test commands. Note coverage gaps, integration boundaries, timing/concurrency behavior, and unusual setup requirements.
7. Use version control history only when useful to clarify intent or hotspots. Write only inside the output directory.
8. if applicable, utilize github MCP or github CLI to gather summary of recent issues and link any wikis available in the repo.

Exclude `.git`, build output, IDE metadata, dependency caches, vendored code, generated sources, certificates, secret files, and large binary assets unless their existence is relevant to the architecture. Mention excluded areas when they affect confidence.

### Monorepo Handling

When step 1 identifies a monorepo, do the following before writing the deliverables:

- **Module map**: enumerate every module declared in the workspace/settings manifest (the manifest, not the directory listing, is the source of truth for what's actually built). For each module capture: path, kind (deployable app / shared library / infra-only module), one-sentence purpose from code evidence, and its internal dependencies on other modules in the repo.
- **Dependency direction**: build a table or list of which modules depend on which other modules (e.g. from each module's build manifest — `implementation(project(":libs:x"))`, `workspace:*` references, `require("../other-package")`). Call out any module that acts as a shared "platform" dependency nearly everything else pulls in, and any module with an unusually narrow consumer set.
- **Shared vs. per-module config**: distinguish root/workspace-level build configuration (shared plugins, toolchain versions, lint/coverage rules, dependency catalogs) from per-module overrides. Note any asymmetries — e.g. a coverage gate or lint rule that's scoped to some modules but not others — since these are common monorepo-specific smells worth surfacing rather than silently normalizing.
- **CI/CD fan-out**: determine whether the repo has one shared pipeline that builds/tests once and fans out into multiple per-module deploy steps, versus fully independent per-module pipelines. Note how many deployable artifacts exist and whether changing one module triggers a rebuild/retest of the whole repo.
- **Cross-module consistency check**: while reading representative files per module, watch for naming, package, or pattern inconsistencies across sibling modules (e.g. a test package copy-pasted from another module, a module missing a convention its siblings follow). Flag these explicitly with file paths rather than silently picking the "canonical" version.
- **Task-oriented entry points**: because a reader is rarely onboarding onto the entire monorepo at once, identify 2-4 common task profiles (e.g. "only working on module X", "adding a shared library feature", "schema/infra change") and the minimal module set each requires.

## Evidence and Analysis Rules

For every substantive claim, prefer a file path and symbol or line range. Label conclusions as:

- **Observed**: directly visible in code or configuration. Always make sure a path to file is available in/at the end of the HTML content.
- **Inferred**: a reasonable interpretation of multiple observations. 

Separate runtime behavior from deployment assumptions. Distinguish public API behavior from internal implementation details. Call out configuration-driven behavior, environment-specific differences, asynchronous or coroutine/thread behavior, retries/timeouts, serialization, validation, caching, idempotency, and failure paths when present.

## Required HTML Content

Build a navigable SPA with a sidebar or tab navigation, readable typography, responsive layout, and small inline infographics made with HTML/CSS/SVG when useful. Keep it self-contained: inline all JavaScript, CSS, and images, with no external fonts, trackers, or network calls. Include:

- Executive summary: purpose, runtime shape, likely consumers, and confidence/unknowns. For a monorepo, state upfront how many deployable modules exist and the one-sentence shape of how they relate (e.g. a pipeline, a set of independent services, a library with example consumers).
- **Monorepo summary** (only when a monorepo was detected in reconnaissance): the module map table (path | kind | purpose | internal dependencies), the dependency-direction findings, shared-vs-per-module config asymmetries, the CI/CD fan-out summary, and the task-oriented entry points list. Use relative file-path text (e.g. `apps/service-name/`) as inline breadcrumbs/labels next to each module so the reader can map a table row to a directory instantly; link to local in-page anchors for any module that gets its own deep-dive subsection.
- System map: entry points, core flow(s), external systems, data movement, and error boundaries. Use an accessible diagram or flow cards, not diagram code that needs compilation. For a monorepo, show entry points per module in one table rather than a single narrative, and make the cross-module data flow (if any) the centerpiece of the flow diagram.
- Language concepts and native APIs: idioms actually used, with short examples and why they matter here.
- Data structures: important enums, classes/records/data classes, interfaces, constants, schemas, and serialization models; explain ownership and lifecycle. In a monorepo, prefer models owned by shared library modules over per-app duplicates, and note which modules the shared models are used from.
- Build system and dependencies: commands, plugins, runtime/toolchain versions, dependency groups, configuration sources, packaging, container/deployment path, and environment differences. In a monorepo, separate root/workspace-level config from per-module config, and give both a whole-repo build command and a single-module build/test/run command.
- Service and transport layer: HTTP/RPC/message consumers, routes/topics, request/response shapes, middleware, health/readiness, auth if observable, timeouts/retries, and downstream gateways. In a monorepo, tabulate this per module (inbound | outbound | HTTP surface) rather than describing one module in depth and glossing over the rest.
- Testing: framework and commands, unit/integration/contract strategy, fixtures and mocks, concurrency/test isolation quirks, flaky or missing coverage, and safe local execution. In a monorepo, tabulate test coverage per module and call out any shared test fixtures/utilities module other modules depend on.
- Core reading path: rank the most complex or central areas, explain why each matters, and give a file/symbol-based reading order. In a monorepo, order this across modules (e.g. shared library first, then the smallest/simplest deployable module, then the most complex one) rather than treating one module as the whole repo.
- Glossary: domain and technical terms used in the repository.
- Evidence index: representative paths with one-line relevance notes.

Use plain language suitable for a college graduate who is new to the team. Explain jargon at first use. Prefer tables, callouts, and short paragraphs over walls of text.

## Required Study Guide

Write a short Markdown guide with flat bullet points, organized under these headings:

- First hour: README, setup/project tools, and the smallest useful execution path. In a monorepo, point at the workspace/settings manifest and the smallest/simplest module as the first full trace, not the whole repo.
- First day: architecture flow, core models, dependencies, configuration, and tests. In a monorepo, include a bullet that traces the full cross-module flow (if one exists) and a bullet comparing per-module dependency manifests to see which modules pull in which shared libraries.
- First week: complex code to read, operational concerns, failure modes, and suggested experiments. In a monorepo, include an experiment that exercises more than one module together (e.g. running two dependent modules against the same local infra) if the architecture supports it.
- Questions for the team: unresolved assumptions, ownership boundaries, production concerns, and missing documentation. In a monorepo, include questions about any cross-module asymmetries or inconsistencies noted during reconnaissance (e.g. a module excluded from a shared quality gate, a naming inconsistency between sibling modules).

Each bullet should contain a concrete action or question and cite a path, command, symbol, or expected observation where possible. Keep it concise enough to use as a checklist.

## Quality Gate

Before finishing:

1. Verify both files exist and contain repository-specific content rather than generic placeholders.
2. Check every required section is present in the HTML and study guide, including the Monorepo Summary section if a monorepo was detected.
3. Confirm links and navigation use local anchors, the HTML works without network access, and mobile layout is usable.
4. Check that claims cite real paths/symbols and that uncertainty is clearly labeled.
5. If a monorepo was detected, confirm every module from the workspace/settings manifest appears in the module map, and that per-module tables (entry points, transport, testing) are not missing any module.
6. Summarize the created files, verification performed, important unknowns, and any reason a deeper investigation is needed.
7. Utilize WCAG 2.2 AA standard for accessible reading.
8. Make sure comments/notes/escaped text doesn't affect rendering of pages.

## Trigger Examples

Use this skill for requests such as:

- “Help me understand this codebase.”
- “I don’t know where to start. Explain this repo and make me a study guide.”
- “Give me the architecture tour, important files, and a beginner-friendly learning path.”
- “I joined this team. Explain this repository and make an HTML architecture tour.”
- “Analyze this service’s language idioms, dependencies, transports, tests, and hardest code to learn.”
- “Create a codebase study guide for a new engineer, including an offline HTML report.”
- “Produce a source-backed onboarding report: trace the key flows, identify operational risks, assess test boundaries, and rank the highest-value files to read.”
- “Help me understand this monorepo and how its apps and shared libraries relate.”
- "help me understand this code"
- "help me get up to speed on this repo"
- "speedrun the codebase"
- "quick pointers about this codebase"
