# Pocket AI — On-Device AI with Claymorphism

[![Build](https://github.com/Shashank2577/pocket-ai/actions/workflows/build_android.yaml/badge.svg)](https://github.com/Shashank2577/pocket-ai/actions)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](LICENSE)
[![Release](https://img.shields.io/github/v/release/Shashank2577/pocket-ai)](https://github.com/Shashank2577/pocket-ai/releases)

A custom-themed fork of [Google AI Edge Gallery](https://github.com/google-ai-edge/gallery) featuring a **high-fidelity Claymorphism design system** and a **Skill Library landing experience**.

## What's Different From Upstream

This fork adds a complete visual and UX layer on top of the original Gallery app:

### Claymorphism Theme Engine
- **4-layer volumetric shadow stack** — deep ambient shadow, specular highlight, inner rim light, and colored bounce light via custom `Modifier.clayShadow`
- **Convex button styling** — `Modifier.clayButton` with gradient fills and layered shadows
- **Bouncy press animation** — `Modifier.clayPressEffect` using spring physics (scale to 0.96 on press, spring back on release)
- **18-color clay palette** — 9 light + 9 dark colors (Vivid Violet, Hot Pink, Sky Blue, Canvas Lavender, and more)
- **Full dark mode** — every clay color has a muted dark variant, wired into `MaterialTheme.colorScheme`

### Skill Library Landing Screen
- **New start destination** — replaces the default home screen as the first thing users see
- **10 real skill cards** — 2 Featured (Mood Music, Restaurant Roulette) + 8 Built-in (Wikipedia, Maps, Mood Tracker, QR Code, Hash, Text Spinner, Kitchen Adventure, Email)
- **Section headers + category badges** — "Featured" and "Built-in" sections with pill badges on each card
- **Clay-styled search bar** — real-time filtering by skill title or description
- **Floating 3D orbs** — gradient-filled circles with colored clay shadows on each card
- **Skill pre-selection** — tapping a card navigates to Agent Chat with that skill auto-selected

### Technical Details
- **Nunito typography** — full weight range (ExtraLight to Black) for rounded, friendly headings
- **LazyColumn** — efficient scrolling for the skill list instead of Column + verticalScroll
- **Thread-safe navigation state** — `PendingSkillSelection` uses `AtomicReference` for cross-screen skill pass-through
- **Compose Previews** — light/dark previews for `SkillLibraryScreen` and `SkillCard`

## Upstream Features (Inherited)

All original Gallery features work as-is:

- **Agent Skills** — LLM + tools (Wikipedia, maps, email, etc.)
- **AI Chat with Thinking Mode** — multi-turn conversations with step-by-step reasoning
- **Ask Image** — multimodal object identification and visual Q&A
- **Audio Scribe** — on-device speech-to-text
- **Prompt Lab** — single-turn prompt testing with parameter controls
- **Mobile Actions** — offline device control via FunctionGemma
- **Tiny Garden** — natural language mini-game
- **Model Management & Benchmark** — download, import, and benchmark models
- **100% On-Device** — all inference is local, no internet required

## Build

1. Create a [HuggingFace Developer Application](https://huggingface.co/docs/hub/oauth#creating-an-oauth-app)
2. In `ProjectConfig.kt`, set your `clientId` and `redirectUri`
3. In `app/build.gradle.kts`, set `appAuthRedirectScheme` to match your redirect URL
4. Build:
   ```bash
   cd Android/src
   ./gradlew assembleRelease
   ```

Or push to GitHub to trigger the [CI build](.github/workflows/build_android.yaml) automatically.

## Project Structure

```
Android/src/app/src/main/java/com/google/ai/edge/gallery/
  ui/
    theme/
      Claymorphism.kt    -- Shadow engine + press animation
      Color.kt           -- Light + dark clay palettes
      Theme.kt           -- Material theme with clay colors
      Type.kt            -- Nunito typography
    skills/
      SkillLibraryScreen.kt      -- Landing screen with search + cards
      PendingSkillSelection.kt   -- Thread-safe skill nav state
    navigation/
      GalleryNavGraph.kt         -- Routing with skill card navigation
  customtasks/agentchat/
    AgentChatScreen.kt   -- Consumes pending skill selection
skills/
  built-in/              -- 8 built-in skills (Wikipedia, Maps, etc.)
  featured/              -- 2 featured skills (Mood Music, Restaurant Roulette)
```

## Based On

This project is a fork of [google-ai-edge/gallery](https://github.com/google-ai-edge/gallery) — Google's official on-device AI demo app. All original functionality, models, and skills are preserved.

## License

Licensed under the Apache License, Version 2.0. See [LICENSE](LICENSE) for details.
