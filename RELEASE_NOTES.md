# Release Notes - High-Fidelity Claymorphism Update

## ✨ New "Digital Clay" Aesthetic
This update introduces a complete visual overhaul of the landing experience, moving away from flat design towards a tactile, high-fidelity **Claymorphism** theme.

### Key Highlights:
*   **Volumetric Shadow Engine**: Implementation of a custom 4-layer shadow stack (Deep Ambient, Specular Highlight, Inner Rim, and Colored Bounce) that gives elements a tangible, physical presence.
*   **Candy Shop Palette**: A new vibrant color system featuring **Vivid Violet**, **Hot Pink**, and **Sky Blue** accents against a warm **Canvas Lavender** background, with full dark mode support.
*   **Skill Library Landing**: A brand-new mobile-first entry screen with 10 real skill cards (2 Featured + 8 Built-in), section headers, category badges, and floating 3D orbs.
*   **Playful Interactivity**: Bouncy spring-animated press effects (`clayPressEffect`) on cards and buttons for tactile, marshmallow-like feedback.
*   **Dark Mode**: Complete clay dark palette with adapted accent colors, theme-aware `SkillLibraryScreen` via `MaterialTheme.colorScheme`.

## 🛠 Technical Changes
*   **Jetpack Compose Integration**: Custom `Modifier.clayShadow`, `Modifier.clayButton`, and `Modifier.clayPressEffect` for reusable volumetric styling and animation.
*   **Theming**: Global `GalleryTheme` updated with light and dark clay palettes, optimized status bar behavior.
*   **Typography**: Optimized hierarchy using **Nunito** for bold, rounded headings.
*   **Navigation**: `SkillLibraryScreen` is now the default start destination with `LazyColumn` for efficient scrolling.

## 🚀 How to Build
1.  Configure your `clientId` and `redirectUri` in `ProjectConfig.kt`.
2.  Set your `appAuthRedirectScheme` in `app/build.gradle.kts`.
3.  Run `./gradlew assembleRelease` or push to your GitHub repo to trigger the automated build runner.
