package com.linhtetko.themoviebookingapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.linhtetko.themoviebookingapp.R
import com.linhtetko.themoviebookingapp.data.model.MovieBookingModel
import com.linhtetko.themoviebookingapp.data.model.MovieBookingModelImpl
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity(R.layout.activity_welcome) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpListener()
        getTokenAndRender()
    }

    private fun getTokenAndRender() {
        MovieBookingModelImpl.getToken {
            if (it != null){
                finishAffinity()
                startActivity(MainActivity.newIntent(this))
            }
        }
    }

    private fun setUpListener() {
        btnWelcomeGetStart.setOnClickListener {
            finishAffinity()
            startActivity(AuthActivity.newIntent(this))
        }
    }
}