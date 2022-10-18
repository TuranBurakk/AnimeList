package com.example.animelist.data

import androidx.room.*


@Dao
interface FavDao {
    @Query("SELECT * FROM model")
    fun  getFav() : List<AnimeModel>
    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun  addToFav(model: AnimeModel)
    @Delete
    suspend fun deleteFromFav(model: AnimeModel)
}