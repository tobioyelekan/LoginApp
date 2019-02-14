package com.example.android.loginapp.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController

import com.example.android.loginapp.R
import com.example.android.loginapp.viewmodel.ProfileViewModel
import kotlinx.android.synthetic.main.profile_fragment.*

class Profile : Fragment() {

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profile_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        viewModel.userInfo.observe(this, Observer { userInfo ->
            userInfo?.let {
                progress.visibility = View.GONE
                contentView.visibility = View.VISIBLE
                fullname.text = it.fname + " " + it.lname
                fname.text = it.fname
                lname.text = it.lname
                email.text = it.email
                phone.text = it.phone
                address.text = it.address
                state.text = it.state
            }
        })

        editProfile.setOnClickListener {
            val editAction = ProfileDirections.actionEditProfile(
                fname.text.toString(), lname.text.toString(), phone.text.toString(),
                email.text.toString(), state.text.toString(), address.text.toString()
            )
            findNavController().navigate(editAction)
        }
    }

}
