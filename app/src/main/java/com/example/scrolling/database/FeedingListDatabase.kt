package com.example.scrolling.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.scrolling.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class FeedingListDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object{
        const val DATABASE_NAME = "user_database"
    }
}