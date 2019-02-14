package com.example.android.loginapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import com.example.android.loginapp.db.UserDatabase
import com.example.android.loginapp.model.UserInfo
import com.example.android.loginapp.repository.UserRepository

class ProfileViewModel(application: Application) : AndroidViewModel(application) {
    private var userRepo: UserRepository

    var userInfo: LiveData<UserInfo>

    init {
        val db = UserDatabase.invoke(application.applicationContext)
        userRepo = UserRepository(db.userDao())
        userInfo = userRepo.getUserInfo()
    }

}
