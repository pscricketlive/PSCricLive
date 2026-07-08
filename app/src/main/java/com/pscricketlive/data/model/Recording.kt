package com.pscricketlive.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * A local video recording made by the Streamer device. Always created regardless of
 * network state — this is the safety net described in SCOPE.md's Streaming Resilience
 * section. The RTMP live push (if any) is a separate concern tracked via rtmpStatus;
 * losing the live push never means losing the recording.
 */
@Entity(tableName = "recordings")
data class Recording(
    @PrimaryKey
    val id: String,                // UUID, generated at creation time
    val matchId: String,           // foreign key -> Match.id
    val localFilePath: String,     // where the file lives on the Streamer device
    val startedAt: Long,           // epoch millis
    val endedAt: Long? = null,     // null while still recording
    val rtmpStatus: RtmpStatus = RtmpStatus.NOT_ATTEMPTED,
    val wasUploadedAsVod: Boolean = false   // manually uploaded later, per SCOPE.md
)

enum class RtmpStatus {
    NOT_ATTEMPTED,   // e.g. offline the whole time — recording-only
    LIVE,            // currently pushing to YouTube/Facebook Live
    RECONNECTING,    // dropped, auto-retrying per SCOPE.md's 30-60s window
    FAILED,          // gave up reconnecting — recording continues regardless
    ENDED
}