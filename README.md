# KAKAU Archive Native v4

Third native milestone. Still 100% Kotlin/Jetpack Compose; no WebView.

## New in v3

- Full backup ZIP including:
  - Room game data
  - all imported covers
  - app settings
- Full restore from that ZIP.
- Settings export/import.
- Persistent theme, default sort/filter and compact-card preference.
- Collections screen:
  - Platforms
  - Genres
  - tap a collection to jump back to the filtered library
- Phone bottom navigation.
- Tablet/large-screen NavigationRail.
- Tablet two-column detail layout.
- Compact card mode.
- Existing XLSX import/export, JSON data backup/restore, statistics, sorting and cover ZIP/folder import remain.

## Recommended workflow for your 600+ cover archive

You can now use either:
- `Import Cover ZIP`, or
- `Import Cover Folder`

The app copies matched covers into its private storage. After matching, create:
`Full Backup + Covers`

That one ZIP becomes the portable backup of the whole archive.

## Full backup format

`KAKAU_Archive_Full_Backup.zip`

Contents:
- `manifest.json`
- `settings.json`
- `games.json`
- `covers/*`

## Responsive UI

Phone:
- Bottom navigation for Library / Stats / Collections / Settings.

Tablet:
- NavigationRail at the left.
- Wider game grids.
- Game detail opens in a split two-column layout.

## Build

- AGP 8.11.0
- Gradle 8.13
- Kotlin 2.1.20
- Java 17
- compileSdk 35
- minSdk 26

Expected debug APK:
`app/build/outputs/apk/debug/app-debug.apk`

## v4 data integrity / build readiness

- Removed the incorrect uniqueness constraint from normalized game titles.
- All 802 source rows remain eligible to exist even when two rows normalize to the same title.
- Cover ZIP/folder import now reports image count, matched rows, and unmatched filenames.
- A single cover may match multiple duplicate-title rows rather than silently dropping source data.
- Added a GitHub Actions workflow that builds a real native debug APK and uploads it as an artifact.
