package com.example.android.loginapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.android.loginapp.R
import com.example.android.loginapp.isValidEmail
import com.example.android.loginapp.toast
import com.example.android.loginapp.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {

    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        viewModel.isUserLoggedIn.observe(this@Login, loggedInObserver)

        login.setOnClickListener {
            if (validate()) {
                val id = viewModel.login(email.text.toString().trim(), password.text.toString().trim())
                when (id) {
                    0 -> {
                        toast("incorrect email or password")
                    }
                    else -> {
                        viewModel.setLoggedIn(id)
                        openMainActivity()
                    }
                }
            }
        }

        register.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
    }

    private val loggedInObserver = Observer<Int> {
        it?.let {
            when (it) {
                0 -> {
                    toast("you are not logged ]in")
                }
                1 -> {
                    openMainActivity()
                }
            }
        }
    }

    private fun openMainActivity() {
        val mainActivity = Intent(this@Login, MainActivity::class.java)
        mainActivity.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        mainActivity.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(mainActivity)
        finish()
    }

    private fun validate(): Boolean {
        var validate = true

        if (TextUtils.isEmpty(email.text)) {
            email.error = "email cannot be empty"
            validate = false
        } else if (!email.text.toString().isValidEmail()) {
            email.error = "email not valid"
            validate = false
        } else {
            email.error = null
        }

        if (TextUtils.isEmpty(password.text)) {
            validate = false
            password.error = "password cannot be empty"
        } else {
            password.error = null
        }

        return validate
    }
}
