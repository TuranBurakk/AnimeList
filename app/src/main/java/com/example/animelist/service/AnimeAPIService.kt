package com.example.animelist.service

import com.example.animelist.data.Model
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


class AnimeAPIService {
    private val BASE_URL = "https://ghibliapi.herokuapp.com"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(AnimeAPI::class.java)

    fun getData(): Single<List<Model>>{
        return api.getAnime()
    }
}