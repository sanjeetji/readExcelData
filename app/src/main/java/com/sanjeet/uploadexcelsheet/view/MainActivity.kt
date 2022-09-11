package com.sanjeet.uploadexcelsheet.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sanjeet.uploadexcelsheet.database.User
import com.sanjeet.uploadexcelsheet.databinding.ActivityMainBinding
import com.sanjeet.uploadexcelsheet.util.Constant.SHARED_PREF
import com.sanjeet.uploadexcelsheet.util.Constant.USER_TYPE
import com.sanjeet.uploadexcelsheet.viewmodel.LoginViewModel

class MainActivity : AppCompatActivity() {

    //test
    private lateinit var binding: ActivityMainBinding
    private var check: Boolean = true
    private var userList: MutableList<User>? = null
    private lateinit var sharedPreferences: SharedPreferences
    lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Test 2 command using readExcelSubBranch2
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(LoginViewModel::class.java)

        sharedPreferences = this.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)

        userList = mutableListOf<User>()
        userList?.add(User(0, "sanju", "123", "admin"))
        userList?.add(User(0, "sanjeet@gmail.com", "123456", "admin"))
        userList?.add(User(0, "pradeep@gmail.com", "123456", "admin"))
        userList?.add(User(0, "sohan@gmail.com", "12345", "user"))
        userList?.add(User(0, "mohan@gmail.com", "12345", "user"))

        check = sharedPreferences.getBoolean("check_key", true)



        if (check) {
            insertUser(userList!!)
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putBoolean("check_key", false)
            editor.apply()
            editor.commit()
        }

        binding.btnLogin.setOnClickListener {

            val email: String = binding.etEmail.text.toString()
            val password: String = binding.etPassword.text.toString()

            if (email.isEmpty()) {
                binding.etEmail.setError("Enter Email")
                binding.etEmail.requestFocus()
                return@setOnClickListener
            } else if (password.isEmpty()) {
                binding.etPassword.setError("Enter Password")
                binding.etPassword.requestFocus()
                return@setOnClickListener
            } else {
                viewModel.getUser(email, password).observe(this, Observer {
                    if (it != null) {
                        val intent = Intent(this, AdminAndUserActivity::class.java)
                        intent.putExtra(USER_TYPE, it.userType)
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            this,
                            "Email and password are not matching",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
            }
        }


    }

    private fun insertUser(user: MutableList<User>) {
        viewModel.insertUsers(user)
    }
}