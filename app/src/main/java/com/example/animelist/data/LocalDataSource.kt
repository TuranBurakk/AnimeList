package com.example.animelist.data

import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val favDao: FavDao) {
        fun getFavorites(): List<Model>{
            return favDao.getFav()
        }

        suspend fun  addToFav(model: Model){
            favDao.addToFav(model)
        }

        suspend fun deleteFromFav(model: Model){
            favDao.deleteFromFav(model)
        }
}