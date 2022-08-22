package com.sanjeet.uploadexcelsheet.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.sanjeet.uploadexcelsheet.database.Student
import com.sanjeet.uploadexcelsheet.database.UserDatabase
import com.sanjeet.uploadexcelsheet.repository.StudentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AdminAndUserViewModel(application:Application):AndroidViewModel(application) {

    var allStudent : LiveData<List<Student>>
    private val repository: StudentRepository

    init {
        val dao = UserDatabase.getDatabase(application).studentDao()
        repository = StudentRepository(dao)
        allStudent = repository.allStudent
    }

    fun insertStudent(student:MutableList<Student>) = viewModelScope.launch(Dispatchers.IO){
        repository.insertStudent(student)
    }

    fun updateStudent(student: Student) = viewModelScope.launch(Dispatchers.IO) {
        repository.upDateStudent(student)
    }

}