package com.example.animelist.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.animelist.data.AnimeModel

@Database(entities = [AnimeModel::class], version = 1)
abstract class AnimeDatabase : RoomDatabase() {

    abstract fun animeDao() : AnimeDao


    companion object{

        @Volatile private var instance : AnimeDatabase? = null

        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock){
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }

        private fun makeDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,AnimeDatabase::class.java,"animedatabase"
        ).build()

    }
}