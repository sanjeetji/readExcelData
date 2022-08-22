package com.sanjeet.uploadexcelsheet.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.sanjeet.uploadexcelsheet.database.User
import com.sanjeet.uploadexcelsheet.database.UserDatabase
import com.sanjeet.uploadexcelsheet.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {


    var allUser: LiveData<List<User>>
    private val repository: LoginRepository

    init {
        val dao = UserDatabase.getDatabase(application).userDao()
        repository = LoginRepository(dao)
        allUser = repository.allUser
    }

    fun insertUsers(user: MutableList<User>) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(user)
    }

    fun getUser(email: String, password: String): LiveData<User> =
        repository.getUser(email, password)


}