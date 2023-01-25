package com.linhtetko.themoviebookingapp

import android.app.Application
import com.linhtetko.themoviebookingapp.data.model.MovieBookingModelImpl

class MovieBookingApp: Application() {

    override fun onCreate() {
        super.onCreate()

        MovieBookingModelImpl.initMovieDatabase(applicationContext)
    }
}