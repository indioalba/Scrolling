package com.example.scrolling.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.scrolling.model.User

@Dao
interface UserDao {

    @Insert
    fun insert(user: User)

    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM user_table")
    suspend fun getUsers() : LiveData<List<User>>

    @Query("DELETE FROM user_table")
    suspend fun deleteAll()

}