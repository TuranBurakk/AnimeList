package com.example.animelist.data

import javax.inject.Inject

class LocalDataSource @Inject constructor(private val favDao: FavDao) {
        fun getFavorites(): List<Model>{
            return favDao.getFav()
        }

        suspend fun  addToFav(animes: Model){
            favDao.addToFav(animes)
        }

        suspend fun deleteFromFav(animes: Model){
            favDao.deleteFromFav(animes)
        }
}