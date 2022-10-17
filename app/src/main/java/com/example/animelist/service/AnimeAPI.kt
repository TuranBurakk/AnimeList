package com.example.animelist.service

import com.example.animelist.data.Model
import retrofit2.Response
import retrofit2.http.GET

interface AnimeAPI {

    @GET("/films")
    suspend fun getAnime():Response<Model>

}