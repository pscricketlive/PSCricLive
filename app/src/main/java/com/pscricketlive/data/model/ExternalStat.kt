package com.pscricketlive.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * A manually-entered aggregate stat total from an external/legacy source (e.g.
 * CricHeroes), shown in a separate "Imported Stats" tab per SCOPE.md — deliberately
 * never merged with stats computed from this app's own Ball records, since mixing
 * verified vs. self-reported numbers would undermine trust in both.
 */
@Entity(tableName = "external_stats")
data class ExternalStat(
    @PrimaryKey
    val id: String,                // UUID, generated at creation time
    val playerId: String,          // foreign key -> Player.id
    val sourceApp: String,         // e.g. "CricHeroes" — free text, not an enum, since
    // this list will grow and isn't worth a migration each time
    val matchesPlayed: Int = 0,
    val runsScored: Int = 0,
    val wicketsTaken: Int = 0,
    val enteredByUserId: String,   // who typed this in — accountability for self-reported data
    val enteredAt: Long            // epoch millis
)