package com.pscricketlive.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pscricketlive.data.model.*

/**
 * The single Room database for the whole app — lists every table (entity) it manages
 * and exposes one DAO getter per table that has a DAO defined so far. Entities without
 * a DAO yet (Team, Player, etc.) are still tracked here so their tables exist; their
 * DAOs get added when a feature actually needs to query them.
 */
@Database(
    entities = [
        Match::class, Team::class, Player::class, Innings::class, Over::class,
        Ball::class, DeviceSession::class, StreamRequest::class, Recording::class,
        ExternalStat::class, Payment::class, Rating::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun matchDao(): MatchDao
    abstract fun inningsDao(): InningsDao
    abstract fun ballDao(): BallDao
}