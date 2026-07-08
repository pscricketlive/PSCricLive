package com.pscricketlive.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * One team's batting innings within a match. A Match has 2 Innings (Team A bats once,
 * Team B bats once) for the limited-overs corporate format this app targets.
 * Runs/wickets are NOT stored here — they're derived from Ball records via
 * ComputeMatchStateUseCase, same reasoning as Match.kt.
 */
@Entity(tableName = "innings")
data class Innings(
    @PrimaryKey
    val id: String,                // UUID, generated at creation time
    val matchId: String,           // foreign key -> Match.id
    val battingTeamId: String,     // foreign key -> Team.id
    val bowlingTeamId: String,     // foreign key -> Team.id
    val inningsNumber: Int,        // 1 or 2 — which innings of the match this is
    val target: Int? = null,       // set for 2nd innings only (1st innings total + 1)
    val isCompleted: Boolean = false
)