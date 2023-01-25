package com.linhtetko.themoviebookingapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.linhtetko.themoviebookingapp.R
import com.linhtetko.themoviebookingapp.adapters.AuthPagerAdapter
import com.linhtetko.themoviebookingapp.data.model.MovieBookingModel
import com.linhtetko.themoviebookingapp.data.model.MovieBookingModelImpl
import com.linhtetko.themoviebookingapp.delegates.LoginDelegate
import com.linhtetko.themoviebookingapp.delegates.RegisterDelegate
import com.linhtetko.themoviebookingapp.fragments.LoginFragment
import com.linhtetko.themoviebookingapp.fragments.SignUpFragment
import com.linhtetko.themoviebookingapp.util.showSnackBar
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : AppCompatActivity(R.layout.activity_auth), LoginDelegate, RegisterDelegate {

    private val movieBookingModel: MovieBookingModel = MovieBookingModelImpl

    companion object {
        const val TAG = "AuthActivity"

        fun newIntent(context: Context) = Intent(context, AuthActivity::class.java)
    }

    private val pages: List<AuthPage> by lazy {
        listOf(
            AuthPage(
                LoginFragment(),
                getString(R.string.lbl_login)
            ), AuthPage(SignUpFragment(), getString(R.string.lbl_sign_up))
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpAuthPages()
    }

    private fun setUpAuthPages() {
        vpAuthPage.adapter = AuthPagerAdapter(this, pages.map { it.fragment })

        TabLayoutMediator(tlAuthCategory, vpAuthPage) { tab, position ->
            tab.text = pages[position].title
        }.attach()
    }

    data class AuthPage(val fragment: Fragment, val title: String)

    private fun onAuthFailure(message: String) {
        clAuth.showSnackBar(message)
    }

    private fun onAuthSuccess(token: String) {
        finishAffinity()
        startActivity(MainActivity.newIntent(this))
    }

    override fun onClickConfirm(email: String, password: String) {
        movieBookingModel.loginWithEmail(email, password,
            onSuccess = {
                it.second?.let { token ->
                    onAuthSuccess(token)
                }
            },
            onFailure = {
                onAuthFailure(it)
            })
    }

    override fun onClickConfirm(name: String, email: String, phone: Long, password: String) {
        movieBookingModel.registerWithEmail(
            name = name,
            email = email,
            phone = phone,
            password = password,
            onSuccess = {
                it.second?.let { token ->
                    // 6765|RfAkRKmR4zN3K5lcHIO8M3KJA267U1AZEVst75Iz
                    onAuthSuccess(token)
                }
            },
            onFailure = {
                onAuthFailure(it)
            }
        )
    }
}