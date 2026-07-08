package com.pscricketlive.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * A request to start streaming a match, requiring App Admin approval before the
 * Streamer device is allowed to go live. Pro-tier users bypass this (see SCOPE.md) —
 * that check happens in the use case that reads this, not by skipping creation of the
 * record itself, so there's always an audit trail either way.
 */
@Entity(tableName = "stream_requests")
data class StreamRequest(
    @PrimaryKey
    val id: String,                     // UUID, generated at creation time
    val matchId: String,                // foreign key -> Match.id
    val requestedByUserId: String,      // who asked to stream
    val status: StreamRequestStatus = StreamRequestStatus.PENDING,
    val requestedAt: Long,              // epoch millis
    val respondedAt: Long? = null,      // null until an admin acts on it
    val respondedByUserId: String? = null   // null until an admin acts on it
)

enum class StreamRequestStatus {
    PENDING,
    APPROVED,
    DENIED
}