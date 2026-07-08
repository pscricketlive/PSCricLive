package com.pscricketlive.data.model

/**
 * Computed live state of an innings, derived from Ball records — never stored as its
 * own source of truth. Produced by ComputeMatchStateUseCase, consumed by the UI and by
 * Firestore sync (this is what Phone B's overlay reads to render the live scoreboard).
 */
data class MatchState(
    val matchId: String,
    val inningsId: String,
    val battingTeamId: String,
    val totalRuns: Int,
    val wicketsLost: Int,
    val oversCompleted: Int,
    val ballsInCurrentOver: Int,      // 0-5 — how far into the current over
    val strikerBatsmanId: String?,
    val nonStrikerBatsmanId: String?,
    val currentBowlerId: String?,
    val target: Int? = null,          // carried over from Innings.target, 2nd innings only
    val runsNeeded: Int? = null,      // target - totalRuns, only meaningful in 2nd innings
    val ballsRemaining: Int? = null   // only meaningful in 2nd innings run-chase
)