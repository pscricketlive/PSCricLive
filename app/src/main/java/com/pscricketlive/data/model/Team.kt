package com.pscricketlive.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * A team. Players belong to a team via Player.teamId.
 * Team-level stats (wins/losses, points table position) are computed on demand,
 * not stored here — see ComputeTeamStatsUseCase.
 */
@Entity(tableName = "teams")
data class Team(
    @PrimaryKey
    val id: String,           // UUID, generated at creation time
    val name: String,
    val shortName: String,    // e.g. "MUM" for scoreboard display — max ~4 chars
    val logoUrl: String? = null,  // optional, nullable — not every corporate team has a logo
    val adminUserId: String   // Team Admin's user id — per SCOPE.md roles table
)