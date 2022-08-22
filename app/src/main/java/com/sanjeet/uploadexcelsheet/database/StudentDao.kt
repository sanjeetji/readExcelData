package com.sanjeet.uploadexcelsheet.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface StudentDao {

    @Insert
    suspend fun insertStudent(student: MutableList<Student>)

    @Query("SELECT * FROM student")
    fun getAllStudent(): LiveData<List<Student>>

    @Update
    suspend fun updateStudent(student: Student)
}