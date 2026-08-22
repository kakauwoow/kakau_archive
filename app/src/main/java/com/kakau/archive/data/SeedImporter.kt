package com.kakau.archive.data

import android.content.Context
import com.kakau.archive.util.GameNormalizer
import org.json.JSONArray

object SeedImporter {
 suspend fun importIfEmpty(context: Context, dao: GameDao) {
  if (dao.count() > 0) return
  val raw = runCatching { context.assets.open("games.json").bufferedReader().use { it.readText() } }.getOrDefault("[]")
  val arr = JSONArray(raw); val rows = ArrayList<GameEntity>(arr.length())
  for (i in 0 until arr.length()) {
   val o=arr.getJSONObject(i); val title=o.optString("title").trim(); if(title.isBlank()) continue
   rows += GameEntity(title=title, normalizedTitle=GameNormalizer.normalize(title), platform=o.optString("Platform"), genre=o.optString("genre"), played=o.optString("played"), adequate=o.optString("adequate"), favourite=o.optString("favourite"), completion100=o.optString("100%"), bestOfBest=o.optString("best of best"), yearReleased=o.optString("YearReleased"), developer=o.optString("Developer"), publisher=o.optString("Publisher"), metacriticRating=o.optString("Metacritic_Rating"), multiplayer=o.optString("Multiplayer"), completionTime=o.optString("CompletionTime"), dlcExpansions=o.optString("DLC_Expansions"), remasteredRemake=o.optString("Remastered_Remake"))
  }
  dao.insertAll(rows)
 }
}
