package com.kakau.archive.data

class GameRepository(private val dao: GameDao) {
 val games = dao.observeAll()
 suspend fun allNow() = dao.getAllNow()
 suspend fun add(game: GameEntity) = dao.insert(game)
 suspend fun update(game: GameEntity) = dao.update(game)
 suspend fun delete(game: GameEntity) = dao.delete(game)
 suspend fun replaceAll(games: List<GameEntity>) { dao.deleteAll(); dao.insertAll(games) }
 suspend fun updateCover(id: Long, path: String) = dao.updateCover(id, path)
 suspend fun updateCoverByNormalizedTitle(normalized: String, path: String) = dao.updateCoverByNormalizedTitle(normalized, path)
 suspend fun countByNormalizedTitle(normalized: String) = dao.countByNormalizedTitle(normalized)
}
