package com.pscricketlive.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * A performance rating for a player in a specific match, submitted by a Team Admin or
 * Captain (see SCOPE.md role table). Visibility is enforced server-side via Firestore
 * security rules, not just hidden in UI — this model only stores the data; the actual
 * access control lives in the Firestore rules config, not here.
 */
@Entity(tableName = "ratings")
data class Rating(
    @PrimaryKey
    val id: String,                // UUID, generated at creation time
    val matchId: String,           // foreign key -> Match.id
    val ratedPlayerId: String,     // foreign key -> Player.id
    val submittedByUserId: String, // Team Admin or Captain who gave the rating
    val score: Int,                // e.g. 1-10 scale
    val comment: String? = null,
    val submittedAt: Long          // epoch millis
)