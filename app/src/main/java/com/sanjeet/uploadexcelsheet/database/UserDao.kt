package com.sanjeet.uploadexcelsheet.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: MutableList<User>?)

    @Query("SELECT * FROM user")
    fun getAllUser(): LiveData<List<User>>

    @Query("SELECT * FROM USER WHERE email LIKE :email AND password LIKE :password")
    fun getUser(email: String, password: String): LiveData<User>

}