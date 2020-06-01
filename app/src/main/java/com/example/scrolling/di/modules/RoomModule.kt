package com.example.scrolling.di.modules

import android.content.Context
import androidx.room.Room
import com.example.scrolling.database.FeedingListDatabase
import com.example.scrolling.database.UserDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RoomModule() {

    @Singleton
    @Provides
    fun providesDatabase(context: Context): FeedingListDatabase {
        return Room.databaseBuilder(
            context,
            FeedingListDatabase::class.java,
            FeedingListDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providesProductDao(database: FeedingListDatabase): UserDao {
        return database.userDao()
    }

}