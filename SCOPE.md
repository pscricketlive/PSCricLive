# PSCricketLive — Project Scope

_This file rarely changes. It's the "what we agreed and why." For current build status, see PROGRESS.md instead._

## Vision
Android app (iOS planned later) for cricket scoring + live streaming, targeting local/corporate
matches — not professional/broadcast-grade. Reference: CricHeroes. Initial use case: the
founder's own corporate matches, with a path to commercialize (likely B2B: corporate
engagement/HR-league angle, not consumer mass-market).

## Hard Constraints
- **Zero cost** for development and running the app. Only unavoidable cost: one-time $25
  Google Play Store registration fee at publish time.
- **Offline-first**: scoring must work with zero network, and sync automatically once
  network returns. This must be genuinely robust — not partial like some competitors.
- **No file over ~100 lines.** One class / one responsibility per file, always.
- **No paid SDKs, no ads, no payment gateway** (payments/ratings are internal tracking only,
  not real money movement, to avoid transaction fees).

## Platform & Stack Decisions (with reasoning)
- **Kotlin native**, not Flutter. Reasoning: streaming/camera/RTMP is the hardest and most
  differentiating part of this app, and Flutter has no mature plugin support for it — you'd
  end up writing native code for that piece regardless. Kotlin also maps closely to the
  founder's existing Java background.
- **iOS is planned, not simultaneous.** Android ships first and gets validated; iOS comes
  later. To keep that cheap later: `domain/usecase/` (all scoring rules, stats, match-state
  logic) is written as pure Kotlin with zero Android-specific imports, so it's reusable via
  Kotlin Multiplatform (KMP) with a future Swift iOS UI. Only the UI layer would need
  rebuilding for iOS, not the actual cricket logic.
- **Room** (local SQLite) — offline source of truth for scoring.
- **Firebase Firestore (free Spark tier)** — cloud backend with built-in offline cache +
  automatic sync when online. This is what makes "auto-sync when network returns" simple
  rather than something we build from scratch.
- **Firebase Auth** via Google Sign-In (not phone/OTP, which has SMS costs beyond a small
  free quota).
- **Firebase Cloud Messaging** — free push notifications (milestones, approvals).
- **RootEncoder** (open-source) — RTMP encoding for streaming to YouTube Live / Facebook
  Live. Instagram is NOT supported — no public RTMP ingest exists for third-party apps;
  this is a platform restriction, not solvable with more engineering effort.

## Two-Device Streaming Model
Avoids needing a dedicated camera rig (a real pain point CricHeroes users have):
- **Phone A (Scorer):** runs scoring UI, writes to Room, syncs to Firestore when online.
  Works fully offline.
- **Phone B (Streamer):** points camera at match, listens to Firestore in real time for
  score updates, renders those as an overlay composited onto camera frames, RTMP-streams
  the result out.
- Pairing via a shareable session code (scorer generates it, streamer enters it to join).
- **Known/accepted limitation:** overlay freezes on last known score if Phone A goes
  offline; Phone A's scoring keeps working regardless of Phone B's network state.

## Streaming Resilience
- Phone B always records locally first, regardless of network — this is the safety net.
- If online, local recording is simultaneously pushed live via RTMP.
- If connection drops mid-broadcast: keep recording locally, auto-retry RTMP reconnect for
  ~30-60s in the background. If that fails, stop the live push but never stop recording.
- Saved recordings can be manually uploaded later as VOD if a live re-stream isn't needed.

## Admin Approval Workflow (Streaming Gate)
- Streaming must never start without explicit approval — implemented as an in-app
  request/approve flow (`StreamRequest` record in Firestore), no separate tool needed.
- **Pro-tier users bypass this gate** (approval only required for free-tier users) — this
  is also the intended monetization hook if/when a paid tier is introduced. Not yet
  decided: whether Pro tier ships in v1 or is deferred.

## User Roles
| Role | Can do |
|---|---|
| App Admin (founder) | Approve/deny stream requests, submit ratings, full visibility |
| Team Admin | Submit ratings for their team's players, manage roster |
| Captain | Submit ratings for their team's players |
| Player | View-only: ratings visible only for players on teams they belong to |

Rating visibility is enforced server-side via Firestore security rules (not just hidden in
UI), since it's a real access-control requirement.

## Feature Set (Phase 1 must-haves)
- Saved squads/rosters
- Ball-by-ball scoring, career + team stats (batting avg, SR, wickets, economy, etc.)
- Shareable scorecard image/text export
- Tournament/league mode with points table
- Milestone push notifications
- Man of the Match / MVP tagging
- Match history feed
- **External/Legacy stats**: manually-entered aggregate totals from other apps (e.g.
  CricHeroes), shown in a separate "Imported Stats" tab, not mixed with native stats
- **Payments tracker**: internal only (who owes what, mark settled manually — no gateway)
- **Ratings tracker**: per roles/visibility rules above

## Competitive Positioning (honest notes, not marketing copy)
- CricHeroes already has offline scoring, streaming, and payments (CricPay) in some form —
  these are NOT unique features on paper. Founder's direct experience is that CricHeroes'
  offline mode is unreliable/requires connectivity in practice more than advertised — our
  differentiation is doing offline **genuinely robustly**, verified end-to-end (e.g. full
  match scored in airplane mode), not claiming a feature that half-works.
- Real differentiation angle: simpler/free two-phone streaming with no dedicated camera,
  no ads/clutter, corporate-league focus, team-scoped rating privacy — not "features
  CricHeroes lacks entirely."
- Commercialization likely means B2B (selling to companies' HR/engagement budgets for
  inter-department leagues), not competing broadly in the consumer cricket-scoring market.

## Codebase Structure
```
data/
  model/        Match.kt, Team.kt, Player.kt, Innings.kt, Over.kt, Ball.kt,
                 MatchState.kt, DeviceSession.kt, StreamRequest.kt, Recording.kt,
                 ExternalStat.kt, Payment.kt, Rating.kt
  local/        MatchDao.kt, InningsDao.kt, BallDao.kt, AppDatabase.kt
  remote/       FirestoreMatchSource.kt, FirestoreSyncManager.kt
  repository/   MatchRepository.kt, ScoringRepository.kt

domain/                         (pure Kotlin, no android.* imports — portable to KMP/iOS)
  usecase/      RecordBallUseCase.kt, StartInningsUseCase.kt, ComputeMatchStateUseCase.kt,
                JoinMatchAsStreamerUseCase.kt, ComputePlayerStatsUseCase.kt,
                ComputeTeamStatsUseCase.kt

ui/
  theme/        Color.kt, Type.kt, Theme.kt
  scoring/      ScoringScreen.kt, ScoringViewModel.kt, BallInputPad.kt
  scoreboard/   ScoreboardView.kt      (reusable — in-app and overlay base)
  streaming/    StreamerScreen.kt, OverlayRenderer.kt      (Phase 2/3)

core/
  BrandConfig.kt
```

## Branding
- App name: **PSCricketLive**, tagline "Score & Broadcast"
- Palette: gold (#B8860B family) on cream (#FBF6E9) background, black nav bar
- Logo: "PS" monogram with bat accent — see `assets/icon_mark.svg` and `logo_full.svg`
- All colors/name centralized in `ui/theme/Color.kt` and `core/BrandConfig.kt` — never
  hardcoded elsewhere, so rebranding later is a one-file change

## Working Model
- Founder (product owner + tester): no prior Android experience, Java + OIC background,
  technical PM. Tests on a real phone via USB (not emulator — i3 laptop, emulator too slow).
- Claude (developer): writes all code in small files, explains Android-specific concepts
  step by step, cannot run/compile Android builds directly — founder builds/runs/tests and
  reports back.
- Code lives in a public GitHub repo so Claude can clone latest state at the start of any
  session without re-pasting code/context.
