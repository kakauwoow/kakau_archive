package com.kakau.archive.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {
 @Query("SELECT * FROM games ORDER BY title COLLATE NOCASE ASC") fun observeAll(): Flow<List<GameEntity>>
 @Query("SELECT * FROM games ORDER BY title COLLATE NOCASE ASC") suspend fun getAllNow(): List<GameEntity>
 @Query("SELECT COUNT(*) FROM games") suspend fun count(): Int
 @Insert(onConflict=OnConflictStrategy.IGNORE) suspend fun insert(game: GameEntity): Long
 @Insert(onConflict=OnConflictStrategy.IGNORE) suspend fun insertAll(games: List<GameEntity>)
 @Update suspend fun update(game: GameEntity)
 @Delete suspend fun delete(game: GameEntity)
 @Query("DELETE FROM games") suspend fun deleteAll()
 @Query("UPDATE games SET coverPath = :coverPath WHERE id = :id") suspend fun updateCover(id: Long, coverPath: String)
 @Query("UPDATE games SET coverPath = :coverPath WHERE normalizedTitle = :normalized") suspend fun updateCoverByNormalizedTitle(normalized: String, coverPath: String): Int
 @Query("SELECT COUNT(*) FROM games WHERE normalizedTitle = :normalized") suspend fun countByNormalizedTitle(normalized: String): Int
}
