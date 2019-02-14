package com.example.android.loginapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.loginapp.db.UserDatabase
import com.example.android.loginapp.model.UserInfo
import com.example.android.loginapp.repository.UserRepository

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private var userRepo: UserRepository

    var isUserLoggedIn: LiveData<Int>

    private var userId = MutableLiveData<Int>()

    init {
        val db = UserDatabase.invoke(application.applicationContext)
        userRepo = UserRepository(db.userDao())
        isUserLoggedIn = userRepo.isUserLoggedIn()
    }

    fun login(email: String, password: String): Int {
        return userRepo.login(email, password)
    }

    fun getUserId(): LiveData<Int> {
        return userId
    }

    fun isEmailExist(email: String): Int {
        return userRepo.isEmailExist(email)
    }

    fun setLoggedIn(userId: Int) {
        userRepo.setUserLoggedIn(userId)
    }

    fun registerUser(user: UserInfo) {
        userRepo.registerUser(user)
    }


}