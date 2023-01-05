package com.example.animelist.service


import com.example.animelist.data.AnimeData
import com.example.animelist.data.AnimeModel
import com.example.animelist.utils.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: AnimeAPI): BaseDataSource(){

    suspend fun getAnime() = getResult {
        apiService.getAnime()
    }
    suspend fun getDetails(id: String)= getResult {
        apiService.getSingleAnime(id)
    }

}