package com.example.animelist.data

import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val favDao: FavDao) {
        fun getFavorites(): List<AnimeModel>{
            return favDao.getFav()
        }

        suspend fun  addToFav(model: AnimeModel){
            favDao.addToFav(model)
        }

        suspend fun deleteFromFav(model: AnimeModel){
            favDao.deleteFromFav(model)
        }
}