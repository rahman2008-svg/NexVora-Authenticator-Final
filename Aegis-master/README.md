# NexVora Authenticator

Offline two-factor authentication (TOTP / HOTP) for Android, published by **NexVora Lab's Ofc**.
Developer: Prince AR Abdur Rahman.

NexVora Authenticator is a **modified version of [Aegis Authenticator](https://github.com/beemdevelopment/Aegis)**
(© Beem Development and contributors), licensed under **GPL-3.0**. It is not independently authored and is not
affiliated with or endorsed by the Aegis project. See [MODIFICATIONS.md](MODIFICATIONS.md), [BRANDING_AUDIT.md](BRANDING_AUDIT.md) and [LICENSE](LICENSE). The upstream README is in `docs/upstream/`.

## What it is
- Application ID `com.nexvora.authenticator`, version `1.0.0` (code `10000`), compile/target SDK 36, min SDK 23
- All authentication logic, the encrypted vault, Android Keystore/biometric unlock, encrypted backups, import/export,
  QR scanning, search, groups, favorites, themes and display modes are the upstream Aegis implementation, preserved as is
- Original NexVora launcher icon, palette and About screen (see MODIFICATIONS.md)
- Works fully offline; no analytics, ads, Firebase or other cloud SDKs were added

## Build
Requires JDK 17+ and Android SDK 36.

    ./gradlew testDebugUnitTest lintDebug assembleDebug      # debug APK in app/build/outputs/apk/debug
    NEXVORA_KEYSTORE_PATH=... NEXVORA_KEYSTORE_PASSWORD=... \
    NEXVORA_KEY_ALIAS=... NEXVORA_KEY_PASSWORD=... ./gradlew assembleRelease

Release signing reads credentials from environment variables only; nothing is stored in the repository.
CI (`.github/workflows/android.yml`) runs on push, pull request and manual dispatch, and builds the release APK only when the
secrets `NEXVORA_KEYSTORE_BASE64`, `NEXVORA_KEYSTORE_PASSWORD`, `NEXVORA_KEY_ALIAS` and `NEXVORA_KEY_PASSWORD` exist.

## Status
This source tree was prepared without an Android SDK or network access, so it has **not been compiled or run on a device**.
Run the CI workflow or the commands above to verify it. Upstream tests are retained in `app/src/test` and `app/src/androidTest`.

## License
GPL-3.0-or-later. Source must accompany any distributed binary. © 2026 NexVora Lab's Ofc for NexVora modifications;
upstream copyrights remain with their owners.
