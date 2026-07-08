package com.pscricketlive.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Pairing record for the two-device streaming model. Scorer device creates this with a
 * shareable code; Streamer device joins by entering that code (see
 * JoinMatchAsStreamerUseCase). Lives in Firestore (not just local Room) since both
 * devices need to see the same record to pair up.
 */
@Entity(tableName = "device_sessions")
data class DeviceSession(
    @PrimaryKey
    val id: String,                // UUID, generated at creation time
    val matchId: String,           // foreign key -> Match.id
    val sessionCode: String,       // short human-shareable code, e.g. "PSC-4821"
    val scorerDeviceId: String,    // generated device identifier for Phone A
    val streamerDeviceId: String? = null,   // null until Phone B joins
    val createdAt: Long,           // epoch millis
    val expiresAt: Long            // epoch millis — codes shouldn't live forever
)