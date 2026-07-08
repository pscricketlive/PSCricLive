package com.pscricketlive.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * A single player. Belongs to a Team via teamId.
 * Career stats are computed on demand (ComputePlayerStatsUseCase), not stored here.
 */
@Entity(tableName = "players")
data class Player(
    @PrimaryKey
    val id: String,           // UUID, generated at creation time
    val name: String,
    val teamId: String,       // foreign key -> Team.id
    val role: PlayerRole = PlayerRole.PLAYER,
    val isActive: Boolean = true
)

enum class PlayerRole {
    APP_ADMIN,
    TEAM_ADMIN,
    CAPTAIN,
    PLAYER
}