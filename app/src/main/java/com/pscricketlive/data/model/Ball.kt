package com.pscricketlive.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * One single delivery. This is the source-of-truth record for the entire app —
 * scores, stats, run rates, everything else is DERIVED from a list of Balls.
 * Recording one Ball = recording one legal cricketing event, nothing more.
 */
@Entity(tableName = "balls")
data class Ball(
    @PrimaryKey
    val id: String,                    // UUID, generated at creation time
    val overId: String,                // foreign key -> Over.id
    val ballNumberInOver: Int,         // 1-6 for legal balls; extras don't increment this
    val strikerBatsmanId: String,      // foreign key -> Player.id
    val nonStrikerBatsmanId: String,   // foreign key -> Player.id
    val runsScored: Int,               // runs off the bat (0-6), excludes extras
    val extraType: ExtraType? = null,  // null = legal ball, no extra
    val extraRuns: Int = 0,            // runs awarded from the extra itself (usually 1)
    val wicket: Wicket? = null,        // null = no wicket fell this ball
    val timestamp: Long                // epoch millis — for offline-first sync ordering
)

enum class ExtraType {
    WIDE, NO_BALL, BYE, LEG_BYE
}

data class Wicket(
    val dismissedPlayerId: String,     // foreign key -> Player.id (striker or non-striker)
    val dismissalType: DismissalType,
    val fielderId: String? = null      // credited on CAUGHT, RUN_OUT, STUMPED — null otherwise
)

enum class DismissalType {
    BOWLED, CAUGHT, LBW, RUN_OUT, STUMPED, HIT_WICKET, RETIRED
}