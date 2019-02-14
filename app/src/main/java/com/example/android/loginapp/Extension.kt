package com.example.android.loginapp

import android.content.Context
import android.widget.Toast

fun String.isValidEmail() =
    this.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun Context.toast(message:String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}