package com.pscricketlive.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * A single match between two teams. Innings/overs/balls all reference this via matchId.
 * Live match state (current score, over, striker) is NOT here — see MatchState.kt,
 * which is recomputed from balls bowled so far rather than tracked as separate mutable state.
 */
@Entity(tableName = "matches")
data class Match(
    @PrimaryKey
    val id: String,               // UUID, generated at creation time
    val teamAId: String,          // foreign key -> Team.id
    val teamBId: String,          // foreign key -> Team.id
    val overs: Int,               // e.g. 20 for T20-style, 0 = unlimited/Test-style
    val venue: String? = null,
    val scheduledAt: Long,        // epoch millis
    val status: MatchStatus = MatchStatus.SCHEDULED,
    val tossWinnerTeamId: String? = null,
    val tossDecision: TossDecision? = null,
    val manOfTheMatchPlayerId: String? = null   // set after match completion
)

enum class MatchStatus {
    SCHEDULED,
    LIVE,
    COMPLETED,
    ABANDONED
}

enum class TossDecision {
    BAT,
    BOWL
}