package com.example.animelist.data

import androidx.room.*


@Dao
interface FavDao {
    @Query("SELECT * FROM model")
    fun  getFav() : List<Model>
    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun  addToFav(animes: Model)
    @Delete
    suspend fun deleteFromFav(animes: Model)
}