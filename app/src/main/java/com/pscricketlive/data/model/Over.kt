package com.pscricketlive.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * One over (6 legal balls, typically) within an innings. Balls reference this via overId.
 * Runs/wickets for the over are derived from its Ball records, not stored here.
 */
@Entity(tableName = "overs")
data class Over(
    @PrimaryKey
    val id: String,              // UUID, generated at creation time
    val inningsId: String,       // foreign key -> Innings.id
    val overNumber: Int,         // 1-indexed: 1st over, 2nd over, etc.
    val bowlerId: String,        // foreign key -> Player.id
    val isMaiden: Boolean = false   // set once over completes with 0 runs off the bat
)