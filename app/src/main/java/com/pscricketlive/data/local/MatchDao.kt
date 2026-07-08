package com.pscricketlive.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.pscricketlive.data.model.Match
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for Match. Defines exactly which SQL queries this app needs —
 * Room generates the actual implementation at compile time (that's what KSP was for).
 */
@Dao
interface MatchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(match: Match)

    @Update
    suspend fun update(match: Match)

    @Query("SELECT * FROM matches WHERE id = :matchId")
    suspend fun getById(matchId: String): Match?

    @Query("SELECT * FROM matches ORDER BY scheduledAt DESC")
    fun getAllMatches(): Flow<List<Match>>

    @Query("SELECT * FROM matches WHERE status = 'LIVE'")
    fun getLiveMatches(): Flow<List<Match>>
}