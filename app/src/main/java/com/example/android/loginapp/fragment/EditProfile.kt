package com.example.android.loginapp.fragment


import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment

import com.example.android.loginapp.R
import com.example.android.loginapp.db.UserDatabase
import com.example.android.loginapp.isValidEmail
import com.example.android.loginapp.repository.UserRepository
import com.example.android.loginapp.toast
import kotlinx.android.synthetic.main.fragment_edit_profile.*

private lateinit var userRepo: UserRepository

class EditProfile : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_edit_profile, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val userDao = UserDatabase.invoke(context!!.applicationContext).userDao()
        userRepo = UserRepository(userDao)

        arguments?.let {
            val safeArgs = EditProfileArgs.fromBundle(it)
            fname.setText(safeArgs.fname)
            lname.setText(safeArgs.lname)
            phone.setText(safeArgs.phone)
            state.setText(safeArgs.state)
            email.setText(safeArgs.email)
            address.setText(safeArgs.address)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.edit_save_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.save -> {
                if (validate()) {
                    save()
                    context?.applicationContext?.toast("profile updated successfully")
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun save() {
        userRepo.updateUser(
            fname.text.toString().trim(), lname.text.toString().trim(), email.text.toString().trim(),
            phone.text.toString().trim(), state.text.toString().trim(), address.text.toString().trim()
        )
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

        return validate
    }
}
