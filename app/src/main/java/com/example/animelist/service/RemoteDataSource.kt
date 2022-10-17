package com.example.animelist.service


import com.example.animelist.service.AnimeAPI
import com.example.animelist.utils.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: AnimeAPI): BaseDataSource(){

    suspend fun getAnime() = getResult {
        apiService.getAnime()
    }
    suspend fun getDetails()= getResult {
        apiService.getAnime()
    }

}