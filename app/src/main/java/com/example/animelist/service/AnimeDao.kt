package com.example.animelist.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.animelist.data.Model

@Dao
interface AnimeDao {

    @Query("SELECT * FROM model")
    suspend fun getAllAnime(): List<Model>

    @Query("SELECT * FROM model WHERE id = :animeId")
    suspend fun getAnime(animeId : String) : Model

    @Query("DELETE FROM model")
    suspend fun deleteAllAnime()
    @Insert
    suspend fun insertAll(vararg animes: Model): List<Long>

}