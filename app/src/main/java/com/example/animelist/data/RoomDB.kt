package com.example.animelist.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AnimeModel::class], version = 1)
abstract class RoomDB : RoomDatabase() {
    abstract fun favoritesDao(): FavDao
}