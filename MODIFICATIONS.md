# Modifications relative to Aegis Authenticator 3.4.3

NexVora Authenticator is a **modified version** of Aegis Authenticator
(https://github.com/beemdevelopment/Aegis, © Beem Development and contributors, GPL-3.0).
The inherited code (OTP engine, vault, crypto, importers, UI) was **not** written by NexVora Lab's Ofc;
NexVora's changes are limited to the items below. Modifications © 2026 NexVora Lab's Ofc, released under the same GPL-3.0 license.

## Release 1.0.0, step 1: identity, About, CI
- `app/build.gradle`: applicationId `com.nexvora.authenticator` (Java namespace `com.beemdevelopment.aegis` unchanged),
  versionName `1.0.0` / versionCode `10000`, launcher titles, git lookup falls back to "unknown" outside a checkout,
  release signing read from environment variables (`NEXVORA_KEYSTORE_PATH`, `_PASSWORD`, `NEXVORA_KEY_ALIAS`, `NEXVORA_KEY_PASSWORD`).
- `res/values/colors.xml`: Material 3 palette changed to NexVora cyan #22D3EE / violet #8B5CF6 on #090D18 / #111827.
- `res/layout/content_about.xml`, `ui/AboutActivity.java`: NexVora developer/publisher/contact card and copyright; upstream
  author credits, GPL and third-party license screens kept; the Play Store link to the upstream listing was removed.
- `.github/workflows/android.yml` added; upstream `build-app-workflow.yaml` and `FUNDING.yml` removed.

## Step 2: icons and branding completion
- **Launcher icons replaced** with original NexVora artwork (gradient shield with keyhole): adaptive background/foreground,
  monochrome (themed icon), legacy square icons, debug variants (with an orange badge), and the 512 px `ic_launcher*-web.png`,
  in mdpi to xxxhdpi. All Aegis icon bitmaps were overwritten.
- Aegis logo vectors/bitmap replaced: `ic_aegis_notification` -> `ic_nexvora_notification`, `ic_aegis_quicksettings` -> `ic_nexvora_quicksettings`,
  `ic_aegis_iconx.png` -> `ic_nexvora_logo.png` (lock screen). References in the manifest, `NotificationService`, `activity_auth.xml` updated.
- `AndroidManifest.xml`: application label now `@string/app_name`.
- `res/values/strings.xml` and all 42 translated `res/values-*/strings.xml`: user-visible "Aegis" replaced by "NexVora"
  (1091 replacements in translations), except import/export format names (see below).
- `res/values-v31/themes.xml`: splash screen background in the NexVora dark color (Android 12+).
- `assets/changelog.html`: added a NexVora 1.0.0 entry; older entries are labeled as upstream Aegis history.
- `VaultRepository` / `VaultBackupManager`: exported and backup file names now start with `nexvora-` instead of `aegis-`.
- `metadata/en-US`: store listing text replaced; Aegis screenshots, icon and icon-pack samples removed.
- `.github/ISSUE_TEMPLATE/bug.yml`: wording updated for NexVora.
- Upstream `README.md`, `CONTRIBUTING.md`, `FAQ.md`, `crowdin.yml` moved to `docs/upstream/` (the Crowdin file targets the upstream project).
- New `README.md`, this file, and `BRANDING_AUDIT.md`.

## Not modified
OTP generation, vault encryption, biometrics, import/export logic, backup logic, QR scanning, database schema, tests.
Nothing here has been compiled or run by the person who prepared it; build and test before release.
