package com.example.android.loginapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import com.example.android.loginapp.db.UserDatabase
import com.example.android.loginapp.repository.UserRepository

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private var userRepo: UserRepository

    var firstName: LiveData<String>

    init {
        val db = UserDatabase.invoke(application.applicationContext)
        userRepo = UserRepository(db.userDao())
        firstName = userRepo.getFirstName()
    }

    fun logout() {
        userRepo.logout()
    }

}
