package com.pscricketlive.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.pscricketlive.data.model.Innings
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for Innings. An innings belongs to a Match (matchId) and tracks
 * which team is batting — see Innings.kt for why runs/wickets aren't stored here.
 */
@Dao
interface InningsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(innings: Innings)

    @Update
    suspend fun update(innings: Innings)

    @Query("SELECT * FROM innings WHERE id = :inningsId")
    suspend fun getById(inningsId: String): Innings?

    @Query("SELECT * FROM innings WHERE matchId = :matchId ORDER BY inningsNumber ASC")
    fun getInningsForMatch(matchId: String): Flow<List<Innings>>

    @Query("SELECT * FROM innings WHERE matchId = :matchId AND isCompleted = 0 LIMIT 1")
    suspend fun getCurrentInnings(matchId: String): Innings?
}