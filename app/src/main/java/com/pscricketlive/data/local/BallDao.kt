package com.pscricketlive.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pscricketlive.data.model.Ball
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for Ball — the source-of-truth table for the entire scoring engine.
 * No update() here deliberately: a recorded ball is treated as immutable history.
 * Corrections happen via undo (delete last ball), not editing a past ball in place.
 */
@Dao
interface BallDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(ball: Ball)

    @Query("DELETE FROM balls WHERE id = :ballId")
    suspend fun deleteById(ballId: String)

    @Query("SELECT * FROM balls WHERE overId = :overId ORDER BY ballNumberInOver ASC")
    fun getBallsForOver(overId: String): Flow<List<Ball>>

    @Query("""
        SELECT balls.* FROM balls
        INNER JOIN overs ON balls.overId = overs.id
        WHERE overs.inningsId = :inningsId
        ORDER BY overs.overNumber ASC, balls.ballNumberInOver ASC
    """)
    fun getBallsForInnings(inningsId: String): Flow<List<Ball>>

    @Query("""
        SELECT balls.* FROM balls
        INNER JOIN overs ON balls.overId = overs.id
        WHERE overs.inningsId = :inningsId
        ORDER BY balls.timestamp DESC
        LIMIT 1
    """)
    suspend fun getLastBall(inningsId: String): Ball?
}