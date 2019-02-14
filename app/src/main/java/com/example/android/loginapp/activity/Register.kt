package com.example.android.loginapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.android.loginapp.R
import com.example.android.loginapp.db.UserDatabase
import com.example.android.loginapp.isValidEmail
import com.example.android.loginapp.model.UserInfo
import com.example.android.loginapp.toast
import com.example.android.loginapp.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {

    private lateinit var viewModel: UserViewModel

    private val userDao by lazy {
        UserDatabase.invoke(applicationContext).userDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        viewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)

        register.setOnClickListener {
            if (validate() and validate2()) {
                registerUser()
            }
        }
    }

    private fun registerUser() {
        val fname = fname.text.toString().trim()
        val lname = lname.text.toString().trim()
        val email = email.text.toString().trim()
        val phone = phone.text.toString().trim()
        val address = address.text.toString().trim()
        val state = state.text.toString().trim()
        val password = pass1.text.toString().trim()

        val user = UserInfo(0, 0, fname, lname, state, email, phone, address, password)

        val exist = viewModel.isEmailExist(email)
        if (exist == 0) {
            viewModel.registerUser(user)
            toast("registered success")
            reset()
        } else {
            toast("user with the same email already exist")
        }
    }

    private fun reset() {
        fname.text = null
        lname.text = null
        email.text = null
        phone.text = null
        address.text = null
        pass1.text = null
        pass2.text = null
        state.text = null
    }

    private fun validate(): Boolean {

        var validate = true
        if (TextUtils.isEmpty(fname.text)) {
            fname.error = "first name cannot be empty"
            validate = false
        } else {
            fname.error = null
        }

        if (TextUtils.isEmpty(lname.text)) {
            lname.error = "last name cannot be empty"
            validate = false
        } else {
            lname.error = null
        }

        when {
            TextUtils.isEmpty(email.text) -> {
                email.error = "email cannot be empty"
                validate = false
            }
            !email.text.toString().isValidEmail() -> {
                email.error = "email not valid"
                validate = false
            }
            else -> email.error = null
        }

        if (TextUtils.isEmpty(state.text)) {
            state.error = "state cannot be empty"
            validate = false
        } else {
            state.error = null
        }

        if (TextUtils.isEmpty(phone.text)) {
            phone.error = "phone cannot be empty"
            validate = false
        } else {
            phone.error = null
        }

        if (TextUtils.isEmpty(address.text)) {
            address.error = "address cannot be empty"
            validate = false
        } else {
            address.error = null
        }

        when {
            TextUtils.isEmpty(pass1.text) -> {
                pass1.error = "password cannot be empty"
                validate = false
            }
            pass1.text.length < 6 -> {
                pass1.error = "password must not be less than 6 characters"
                validate = false
            }
            else -> pass1.error = null
        }

        when {
            TextUtils.isEmpty(pass2.text) -> {
                pass2.error = "password cannot be empty"
                validate = false
            }
            pass2.text.length < 6 -> {
                pass2.error = "password must not be less than 6 characters"
                validate = false
            }
            else -> pass2.error = null
        }

        return validate
    }

    private fun validate2(): Boolean {
        var bool = true
        if (pass1.text.toString() != pass2.text.toString()) {
            pass1.error = "password do not match"
            pass2.error = "password do not match"
            bool = false
        } else {
            pass1.error = null
            pass2.error = null
        }

        return bool
    }
}
