package com.example.animelist.service

import com.example.animelist.data.Model
import io.reactivex.Single
import retrofit2.http.GET

interface AnimeAPI {

    @GET("/films")
    fun getAnime():Single<List<Model>>

}