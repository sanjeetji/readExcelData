package com.sanjeet.uploadexcelsheet.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student")
data class Student(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    val name: String,
    val gender: String,
    val city: String,
    val status: String
)