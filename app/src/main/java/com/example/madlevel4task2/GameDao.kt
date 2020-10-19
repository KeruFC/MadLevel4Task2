package com.example.madlevel4task2

import androidx.room.*

@Dao
interface GameDao {

    @Query("SELECT * FROM match_table")
    suspend fun getAllGames(): List<Game>

    @Insert
    suspend fun insertGame(game: Game)

    @Delete
    suspend fun deleteGame(game: Game)

    @Query("DELETE FROM match_table")
    suspend fun deleteAllGames()
}