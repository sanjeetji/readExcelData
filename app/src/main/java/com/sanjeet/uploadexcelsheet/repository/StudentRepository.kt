package com.sanjeet.uploadexcelsheet.repository

import androidx.lifecycle.LiveData
import com.sanjeet.uploadexcelsheet.database.Student
import com.sanjeet.uploadexcelsheet.database.StudentDao

class StudentRepository(private val studentDao: StudentDao) {


    val allStudent: LiveData<List<Student>> = studentDao.getAllStudent()

    suspend fun insertStudent(student: MutableList<Student>) {
        studentDao.insertStudent(student)
    }

    suspend fun upDateStudent(student: Student) {
        studentDao.updateStudent(student)
    }
}