package com.example.android.loginapp.fragment

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.example.android.loginapp.R
import com.example.android.loginapp.activity.Login
import com.example.android.loginapp.activity.MainActivity
import com.example.android.loginapp.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.home_fragment.*

class Home : Fragment() {

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        viewModel.firstName.observe(this, Observer {
            it?.let {
                welcome.text = "Welcome $it"
            }
        })

        logout.setOnClickListener {
            viewModel.logout()

            val mainActivity = Intent(context, Login::class.java)
            mainActivity.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            mainActivity.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(mainActivity)
            activity?.finish()
        }

    }

}
