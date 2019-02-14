package com.example.android.loginapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.android.loginapp.model.UserInfo

@Dao
interface UserDao {

    @Insert
    fun registerUser(user: UserInfo)

    @Query("select count(status) from user_table where status = 1")
    fun isUserLoggedIn(): LiveData<Int>

    @Query("select fname from user_table where status = 1")
    fun getFirstName(): LiveData<String>

    @Query("select id from user_table where email = :user_email and password = :user_password")
    fun login(user_email: String, user_password: String): Int

    @Query("select count(email) from user_table where email = :regEmail")
    fun isEmailExist(regEmail: String): Int

    @Query("update user_table set status = 1 where id = :userId")
    fun loggedIn(userId: Int)

    @Query("update user_table set status = 0 where status = 1")
    fun logout()

    @Query("select * from user_table where status = 1")
    fun getUserInfo(): LiveData<UserInfo>

    @Query("update user_table set fname= :user_fname, lname= :user_lname, email= :user_email, phone= :user_phone, state= :user_state, address= :user_address")
    fun updateUser(
        user_fname: String, user_lname: String, user_email: String, user_phone: String,
        user_state: String, user_address: String
    )
}