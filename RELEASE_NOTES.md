# Release Notes - High-Fidelity Claymorphism Update

## ✨ New "Digital Clay" Aesthetic
This update introduces a complete visual overhaul of the landing experience, moving away from flat design towards a tactile, high-fidelity **Claymorphism** theme.

### Key Highlights:
*   **Volumetric Shadow Engine**: Implementation of a custom 4-layer shadow stack (Deep Ambient, Specular Highlight, Inner Rim, and Colored Bounce) that gives elements a tangible, physical presence.
*   **Candy Shop Palette**: A new vibrant color system featuring **Vivid Violet**, **Hot Pink**, and **Sky Blue** accents against a warm **Canvas Lavender** background.
*   **Skill Library Landing**: A brand-new mobile-first entry screen with super-rounded Bento cards and floating 3D orbs.
*   **Playful Interactivity**: High-convexity buttons and "marshmallow-like" surfaces that respond to the environment.

## 🛠 Technical Changes
*   **Jetpack Compose Integration**: Custom `Modifier.clayShadow` and `Modifier.clayButton` for reusable volumetric styling.
*   **Theming**: Global `GalleryTheme` updated with the new clay palette and optimized status bar behavior.
*   **Typography**: Optimized hierarchy using **Nunito** for bold, rounded headings.
*   **Navigation**: `SkillLibraryScreen` is now the default start destination for a more welcoming first-run experience.

## 🚀 How to Build
1.  Configure your `clientId` and `redirectUri` in `ProjectConfig.kt`.
2.  Set your `appAuthRedirectScheme` in `app/build.gradle.kts`.
3.  Run `./gradlew assembleRelease` or push to your GitHub repo to trigger the automated build runner.
