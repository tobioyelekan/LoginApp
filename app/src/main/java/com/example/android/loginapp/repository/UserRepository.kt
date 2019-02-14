package com.example.android.loginapp.repository

import androidx.lifecycle.LiveData
import com.example.android.loginapp.db.UserDao
import com.example.android.loginapp.model.UserInfo
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.doAsyncResult

class UserRepository(private val userDao: UserDao) {

    fun isUserLoggedIn(): LiveData<Int> = userDao.isUserLoggedIn()

    fun login(email: String, password: String): Int {
        return doAsyncResult {
            userDao.login(email, password)
        }.get()
    }

    fun isEmailExist(email: String): Int {
        return doAsyncResult {
            userDao.isEmailExist(email)
        }.get()
    }

    fun registerUser(user: UserInfo) {
        doAsync {
            userDao.registerUser(user)
        }
    }

    fun getFirstName(): LiveData<String> {
        return userDao.getFirstName()
    }

    fun getUserInfo(): LiveData<UserInfo> {
        return userDao.getUserInfo()
    }

    fun setUserLoggedIn(userId: Int) {
        doAsync {
            userDao.loggedIn(userId)
        }
    }

    fun logout() {
        doAsync {
            userDao.logout()
        }
    }

    fun updateUser(
        user_fname: String, user_lname: String, user_email: String, user_phone: String,
        user_state: String, user_address: String
    ) {
        doAsync {
            userDao.updateUser(user_fname, user_lname, user_email, user_phone, user_state, user_address)
        }
    }
}