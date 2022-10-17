package com.example.animelist

import com.example.animelist.service.AnimeAPI
import com.example.animelist.service.RemoteDataSource
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideApiService(retrofit: Retrofit): AnimeAPI {
        return retrofit.create(AnimeAPI::class.java)
    }

    @Provides
    fun provideRetrofit(
        gson: Gson,
        baseUrl: BaseUrl,
        client: OkHttpClient
    ): Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl.url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor {
                val request = it.request().newBuilder().build()
                it.proceed(request)
            }
            .build()
    }

    @Provides
    fun provideGson(): Gson {
        return  Gson()
    }

    @Provides
    fun provideApiDataSource(
        apiService: AnimeAPI
    ): RemoteDataSource{
        return RemoteDataSource(apiService)
    }

    @Provides
    fun provideBaseUrl(): BaseUrl{
        return BaseUrl("https://ghibliapi.herokuapp.com")
    }
}

data class BaseUrl(val url: String)