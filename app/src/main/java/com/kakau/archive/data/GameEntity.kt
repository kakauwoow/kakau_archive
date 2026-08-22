package com.kakau.archive.data

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "games", indices = [Index(value=["title"]), Index(value=["normalizedTitle"]), Index(value=["platform"]), Index(value=["genre"])])
data class GameEntity(
 @PrimaryKey(autoGenerate=true) val id: Long = 0,
 val title: String, val normalizedTitle: String, val platform: String = "", val genre: String = "",
 val played: String = "", val adequate: String = "", val favourite: String = "", val completion100: String = "",
 val bestOfBest: String = "", val yearReleased: String = "", val developer: String = "", val publisher: String = "",
 val metacriticRating: String = "", val multiplayer: String = "", val completionTime: String = "",
 val dlcExpansions: String = "", val remasteredRemake: String = "", val coverPath: String = ""
)
