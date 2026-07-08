# PSCricketLive — Progress Tracker

_Update this file at the end of every session. Claude should read this first in any new
chat to know exactly where things stand — check SCOPE.md only for "what/why" background._


## Current Status (as of 2026-07-08)
**Phase 1: Class creations** — in progress
- [x] Match.kt, Team.kt, Player.kt, Innings.kt, Over.kt, Ball.kt,
                 MatchState.kt - created
- [x] MatchDao.kt, InningsDao.kt, BallDao.kt, AppDatabase.kt, Converters.kt - created

## Previous Status (as of 2026-07-08)
**Phase 0: Environment Setup** — Completed
- [x] Project spec, data model, phased roadmap agreed
- [x] Theme files drafted (Color.kt, Type.kt, Theme.kt, BrandConfig.kt) — added to
      an actual Android Studio project
- [x] Logo assets created (icon_mark.svg, logo_full.svg)
- [x] Android Studio installed
- [x] First project created (`PSCricketLive`, package `com.pscricketlive`, Empty Activity,
      Kotlin) — created
- [x] First run confirmed (on emulator pixel 8 running on android 14)
- [x] Theme files wired into project
- [x] GitHub repo created and connected (Android Studio VCS → Share Project on GitHub)
      


## Decisions Locked So Far
- Kotlin native, not Flutter (see SCOPE.md for reasoning)
- Android first, iOS later via KMP — not simultaneous
- Testing via real device (USB) preferred over emulator; unit tests + Compose Preview for
  early Phase 1 work that doesn't need a device at all
- Public GitHub repo (not private) — no secrets belong in code anyway

## Not Yet Decided / Open Questions
- Whether a paid "Pro" tier ships in v1 (bypasses streaming approval gate) or is deferred
- Exact GitHub repo name/URL (create during setup, then paste here)
- Whether external stats entry supports bulk import (e.g. CSV) or one-by-one manual entry
  only for v1

## Next Session Should Start With
1. Continue actual Phase 1 code: remaining `data/model/` classes , and other classes

## Session Log
- **2026-07-08**: Full spec created and iterated — vision, two-device model, phased
  roadmap, tech stack, data model, roles, streaming resilience, admin approval workflow,
  external stats, payments/ratings. Branding created (PSCricketLive, gold/cream theme,
  logo). Theme starter Kotlin files drafted. Discussed Kotlin vs Flutter, Android-first vs
  iOS-simultaneous, competitive positioning vs CricHeroes (corrected assumption that
  CricHeroes is "online only" — it has partial/unreliable offline mode, not none).
  Founder has laptop (i3/20GB/1TB) — sufficient, but should avoid emulator, use real device
  or unit tests instead. GitHub repo not yet created.
