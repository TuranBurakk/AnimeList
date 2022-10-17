package com.example.animelist.data

import com.example.animelist.service.RemoteDataSource
import com.example.animelist.utils.performNetworkOperation
import javax.inject.Inject

class Repository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {

    fun getAnimes() = performNetworkOperation {
        remoteDataSource.getAnime()
    }
    fun getdetails(id : String) = performNetworkOperation {
        remoteDataSource.getDetails()
    }

    fun getFav()= localDataSource.getFavorites()

    suspend fun addToFavorites(animes : Model) = localDataSource.addToFav(animes)

    suspend fun deleteFromFavorites(animes : Model) = localDataSource.deleteFromFav(animes)
}