package com.example.animelist.data

import javax.inject.Inject

class Repository @Inject constructor(private val localDataSource: LocalDataSource) {
    fun getFav()= localDataSource.getFavorites()
    suspend fun addToFavorites(animes : Model) = localDataSource.addToFav(animes)

    suspend fun deleteFromFavorites(animes : Model) = localDataSource.deleteFromFav(animes)
}