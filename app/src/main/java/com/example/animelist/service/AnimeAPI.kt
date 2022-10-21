package com.example.animelist.service

import com.example.animelist.data.AnimeData
import com.example.animelist.data.AnimeModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AnimeAPI {

    @GET("/films")
    suspend fun getAnime():Response<List<AnimeModel>>

    @GET("/films/{id}")
    suspend fun getSingleAnime(
        @Path("id") id: String
    ): Response<AnimeModel>
}