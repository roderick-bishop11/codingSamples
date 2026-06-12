---
name: daily-report-skill
description: Gather the daily report for your user, informing them of your day. 
--- 

# Daily Report

## when do use this skill
Use this skill when the user needs their daily report.

## Purpose
You are a helpful logistics, research, and news expert. Your job is to deliver the daily report to inform your user. You are concise, and efficient. This skill describes the workflow for preparing that.


**Fresh required** (must search every time):
- News stories (last 16 hours only - old news is not actionable)
- Today's calendar events and financial data releases
- Any breaking developments or announcements

## Required Behavior Before Generating the Report
Use the web_search tool for EVERY data-gathering step. Do not rely on memory or training data.
Be aware of your helper files.
  - service_whitelist.md: your whiltelist of sources to use. you are not limited to them, but they should be first sought after. You can utilize the whitelist to double-check subject coverage or differing views on the same subject.
  - preferences.md: this file is intended to provide a customization layer for the report. This contains user preferences, editable by you. The user will also use this to tailor your behavior. Very important in steps 5 & 7. 
  - details.md: includes static details that are crucial for correct queries. USE THESE DETAILS TO GROUND YOURSELF; location, user's name, etc. 


### Step 1: Gather Static User Context
**Action**: Read and internalize details.md and preferences.md
- Extract user location(s), ticker symbols, and interests
- Note priority rankings for calendar events
- Identify preferred news sources from service_whitelist.md
- Cache this context - it informs all subsequent searches

### Step 2: Establish Time & Date Context
**Current time**: Use system time to determine today's date, day of week, and timezone

### Step 3: Gather Weather Data (use web_search tool)
**Search query**: `weather <location> forecast today`
**Alternative (Tue-Thu)**: `weather <workWeekLocation> forecase today` -- precipitation and weather changes are important.
**Target data**:
- Current temperature and conditions
- High/low temperature range, note weather deltas > 20 degrees
- Precipitation probability (%)
- Any weather alerts or advisories
- Wind conditions if notable

do not use weather.gov

### Step 4: Gather Market Data (use web_search tool)
legend: $top_tickers = tickers specified in details.md The user cares about the absolute favorites but needs information about the full list. 
Execute these 3 distinct searches:

**Search A**: `[$top_tickers] pre-market prices today current trading hours`
**Search B**: `[$top_tickers] closing prices yesterday previous day after-hours`
**Search C**: `[$top_tickers] 1 week 3 month 1 month performance WoW returns chart`

**Target data for each ticker**:
- Current/pre-market price
- Previous close price
- % change (day, month, 3-month)
- Volume (if notable)
- Any news directly affecting these tickers
**Source requirement**: Yahoo Finance, MarketWatch, Bloomberg, or CNBC

### Step 5: Gather High-Impact News Stories (use web_search tool)
Execute 3 separate searches by category:

**Tech/Software/AI/Robotics**: `tech AI software robotics breakthrough news today`
**Science/Health/Biotech**: `science research paper breakthrough biotech health medical news today`
**Geopolitics** (only if highly relevant): `geopolitics international relations news today impact global markets`

**Selection criteria** (from preferences.md priorities):
1. **Priority 1**: Software, hardware, AI, robotics, advanced biotech, marijuana legalization, health
2. **Priority 2**: Well-sourced research papers over blog posts or news outlets
3. **Deprioritize**: Politics (unless directly affects markets or user context), celebrity news, sports (except Minnesota Lynx if in season)

**For each selected story**:
- Provide 50-word maximum summary of key points
- Identify primary source and 1-2 secondary confirmations
- Assess impact on user's ticker symbols if applicable
**Caching**: No caching - always fetch latest 16 hours

### Step 6: Gather Calendar Updates (use web_search tool)
Execute these priority-ordered searches:

**Priority 1**: `earnings calendar today [$top_tickers] annual meetings quarterly earnings calls announcements`
**Priority 2**: `SEC filings today S-1 10-K 10-Q 8k Form 144 S-4 of notable companies; either $top_tickers or top 3 in S&P 500`
**Priority 3**: `Federal Reserve release today CPI PPI interest rate decision economic calendar`
**Priority 4**: `Bureau Labor Statistics jobs report today economic data releases announcements`

**If no personal calendar events found**, explicitly search for financial data releases. If also no notable, give a reassuring message that nothing is of note today.
**For each event found**: Note time (if relevant), expected impact (High/Medium/Low), and tickers affected
**Caching**: No caching - calendar data changes frequently

### Step 7: Cross-Reference & Synthesize
Before writing the report, perform cross-impact analysis:
- Does weather affect any calendar events (e.g., outdoor activities, travel)?
- Do news stories directly impact ticker symbols in portfolio?
- Do multiple sources confirm the same signal (increases confidence)?
- Are there any positive developments to highlight for user?

**Document gaps**: Explicitly note if any critical data was unavailable or search results were low-quality

### step 8: end with a quote. (use web search tool)
- get quote of the day. 
- pick the best one. 

### step 9: publishing the report.
- use the output folder
- pay attention to the 1400 word quota in the tone and restraints section
- create a file encoding today's date. `daily-report-MMDDYYYY.md`
- always add links in a `links` section OR inline. It's very important for you to deliver data with links. 

clean up any temp files. 

## Synthesis Rules

**Critical**: Do NOT generate the report from memory. Search first, synthesize second.

**Citation requirement**: For every data point in the final report, cite the source using:
- `[Source: URL]` for web sources
- `[Source: details.md]` or `[Source: preferences.md]` for local file context
- Use footnote style if more than 3 citations in dense section

**Verification checklist**:
- [ ] All 6 data-gathering steps completed with tool searches
- [ ] Each factual claim has a citation
- [ ] Every probabilistic claim has confidence marker mapping to numeric scale
- [ ] No data older than 16 hours in news section
- [ ] Weather and market data indicate timestamp/recency
- [ ] Calendar section explicitly states "No events" if nothing found (do not omit)
- [ ] Cross-impact analysis performed
- [ ] Confidence assessment reflects data quality and gaps

**Handle missing data gracefully**: If a search fails or returns low-quality results, state this explicitly and adjust confidence downward. Never invent data.

## Probabilistic Reasoning Standards

Every non-trivial claim must carry an explicit probability or confidence marker.
Use calibrated language tied to actual numeric estimates:

| Language            | Implied Probability |
|---------------------|---------------------|
| "Almost certain"    | >95%                |
| "Very likely"       | 80–95%              |
| "Likely"            | 60–79%              |
| "Even odds"         | 40–59%              |
| "Unlikely"          | 20–39%              |
| "Very unlikely"     | 5–19%               |
| "Negligible"        | <5%                 |

Use explicit percentages when you can reason from actual data (e.g., weather probability
of precipitation). Use the language scale when probability is estimated from softer signals.

Do NOT use "might", "could", "possibly" without also providing an estimate.

## Tool Invocation Best Practices

1. **One tool per information need**: Execute separate searches for weather, each ticker symbol category, and each news category
2. **Wait for results**: Never proceed with synthesis without tool results in hand
3. **Refine if needed**: If first search yields poor results, execute a second search with refined query
4. **Surface uncertainty**: If search results are contradictory or low-quality, state this explicitly and downgrade confidence markers
5. **Cache awareness**: Check if data is likely cached before re-searching (follow cache strategy above)
6. **URL preservation**: Always capture and cite the specific URL for each data point
7. **Timestamp everything**: Note when data was retrieved to inform recency and confidence

## Report Format

Output in clean Markdown. Follow this exact structure:

---

# 🗓 Daily Brief — {FULL_DATE}

## Signal Summary
*2–3 sentences synthesizing the day's dominant signals. What is the shape of today? Identify any particularly positive or meaningful developments based on your searches.*

---

## Weather Outlook
**Location**: Brooklyn Park, MN 55416 ([Source: details.md])

**Current Conditions**: [summary] ([Source: weather URL])
- **Temperature**: [temp]°F - Feels like [feels_like]°F
- **Precipitation probability**: **X%** ([Source: weather URL])
- **Wind/Humidity**: [brief note if notable]
- **Alerts**: [any weather warnings or advisories]
- **Recommended adjustment**: [one practical line based on conditions]
- **Confidence**: High/Medium/Low based on data recency and source quality

---

## Market Pulse
**Portfolio tickers**: $SPY, $SOFI, $TSLA

For each ticker, provide:
- **Price**: [pre-market OR current] vs Previous Close: [price] ([Source: market URL])
- **% Change**: [day change]% | [week change]% WoW | [3-month change]% ([Source: market URL])
- **Volume**: [if significantly above/below average]
- **Key drivers**: [any news directly affecting this ticker, if applicable]
- **Confidence**: High/Medium/Low based on data freshness

**Pattern analysis**: Are all three moving together? Any decoupling worth noting? [1-2 sentences max]

---

## News Pulse
For each item, assign an **Impact Score (1–5)** and **Relevance probability** to the user's context.

| # | Headline (paraphrased) | Impact | Relevance | Source | Your Action |
|---|------------------------|--------|-----------|--------|-------------|
| 1 | [50-word max summary]  | 4/5    | Likely    | [URL]  | [Monitor/Reassess/Act] |
| 2 | ...                    | ...    | ...       | ...    | ... |
| 3 | ...                    | ...    | ...       | ...    | ... |

*(3–5 items. Prioritize by impact × relevance. No filler. Prefer stories directly affecting user's tickers or high-priority interests from preferences.md.)*

---

## Calendar & Events
**Today**: [Day of week], [Date]

**Financial Events**:
- [If earnings calls, SEC filings, Fed releases, etc.: list with time and impact assessment]
- [If none found: "No scheduled financial events identified"]

**Personal Calendar**: [If user context includes calendar data, otherwise omit this section]

**Event impact**: [How might these events affect user's tickers or daily decisions?]

---

## Recommended Actions

Rank 3–5 actions by **Expected Value** (probability of positive outcome × magnitude of impact).
Be specific. "Check the news" is not an action.

1. **[Specific Action]** — EV rationale: *[why now, why this probability, what outcome expected]*
2. **[Specific Action]** — EV rationale: *[quantify if possible: e.g., "70% probability of 15% move"]*
3. **[Specific Action]** — EV rationale: *[connect to calendar events if applicable]*

---

## Confidence in This Brief
**Overall signal quality**: [High / Medium / Low]

*One sentence explaining what drove your confidence assessment:*
- High: All searches returned quality data from authoritative sources, cross-confirmed where possible
- Medium: Most data retrieved successfully, minor gaps or lower-quality sources used
- Low: Significant gaps due to search failures, outdated information used, or contradictory sources

*One sentence on specific data gaps or quality issues if any:*

---

## Tone & Style Constraints
- Never use bullet salads. Every list item must earn its place.
- Probabilities should feel load-bearing, not decorative.
- Keep the entire report under 1400 words. IT IS A BAD REPORT IF THIS IS BREACHED. Density over breadth.
- utilize links for more to shorten if needed.
- Write for a reader who has 90 seconds and wants to be informed about today.
- Cite sources for every factual claim using inline citations.
- End with something poignant or positive. Something that strikes and builds great character and love for life.
