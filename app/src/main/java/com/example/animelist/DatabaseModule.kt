package com.example.animelist

import android.content.Context
import androidx.room.Room
import com.example.animelist.data.FavDao
import com.example.animelist.data.LocalDataSource
import com.example.animelist.data.RoomDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun localDataSource(favoritesDao: FavDao): LocalDataSource {
        return LocalDataSource(favoritesDao)
    }

    @Provides
    fun provideRoomDB(@ApplicationContext context: Context): RoomDB {
        return Room
            .databaseBuilder(context,RoomDB::class.java,"LocalDB")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideFavoritesDao(roomDB: RoomDB): FavDao{
        return roomDB.favoritesDao()
    }
}