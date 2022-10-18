package com.example.animelist.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.animelist.data.AnimeModel

@Dao
interface AnimeDao {

    @Query("SELECT * FROM model")
    suspend fun getAllAnime(): List<AnimeModel>

    @Query("SELECT * FROM model WHERE id = :animeId")
    suspend fun getAnime(animeId : String) : AnimeModel

    @Query("DELETE FROM model")
    suspend fun deleteAllAnime()
    @Insert
    suspend fun insertAll(vararg animes: AnimeModel): List<Long>

}