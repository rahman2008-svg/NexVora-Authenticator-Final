# Branding audit

## Replaced with NexVora branding
App name and label, launcher icons (all densities, adaptive, monochrome, debug, web), notification/quick-settings icon,
lock-screen logo, splash background, theme palette, About screen, user-visible strings in English and 42 translations,
export/backup file name prefixes, changelog header, store listing text, issue template wording.

## Aegis references intentionally kept
| Where | Why |
|---|---|
| `LICENSE`, license dialog, About card text "modified version of Aegis", `docs/upstream/*` | GPL-3.0 requires keeping the license, copyright and attribution |
| About screen: original authors (Alexander Bakker, Michael Schättgen) and upstream GitHub link | Required attribution |
| Java package `com.beemdevelopment.aegis`, class names such as `AegisApplication`, theme/style names `*.Aegis.*` | Internal identifiers, not user-visible; renaming adds risk with no benefit |
| `aegis.json` (internal vault file), `aegis-db` (Room database), `backup/aegis.json` in backup rules | Internal storage names |
| "Aegis (.JSON)" export format, "Import Aegis vault", importer help/password strings for Aegis | Compatibility: Aegis-format vaults can be imported and exported |
| `pref_section_import_export_summary`: "Import backups of Aegis or other apps" | Names the Aegis import source |
| `no_icon_packs` string linking aegis-icons | Third-party icon pack project is named Aegis Icons |
| `docs/vault.md`, `docs/decrypt.py`, `docs/iconpacks.md` | Document the Aegis vault/icon-pack file formats |
| Older `changelog.html` entries | Upstream release history, now labeled as such |
| `.github/workflows/codeql-analysis.yml` | Generic, no branding |
| Third-party license data in `res/raw` (AboutLibraries) | Required notices |

## Caveats
- Translated strings were rewritten mechanically (Aegis -> NexVora); a few in inflected languages may read slightly awkwardly, and
  translated strings that mention the Aegis import format were changed too if their key did not contain "aegis". Review if you ship translations.
- The in-app "GitHub" row still links to the upstream Aegis repository (source/attribution). Replace it with your own repository URL once published
  if you want, but keep an upstream credit.
- Nothing was built or run; icons were checked visually from a rendered preview only.
