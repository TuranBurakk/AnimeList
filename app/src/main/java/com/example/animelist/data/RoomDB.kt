package com.example.animelist.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Model::class], version = 4)
abstract class RoomDB : RoomDatabase() {
    abstract fun favoritesDao(): FavDao
}