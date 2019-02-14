package com.example.android.loginapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserInfo(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val status: Int,
    val fname: String,
    val lname: String,
    val state: String,
    val email: String,
    val phone: String,
    val address: String,
    val password: String
)