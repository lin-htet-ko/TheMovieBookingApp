package com.linhtetko.themoviebookingapp.persistance

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.linhtetko.themoviebookingapp.data.vos.moviebookingapp.*
import com.linhtetko.themoviebookingapp.data.vos.moviedb.GenreVO
import com.linhtetko.themoviebookingapp.data.vos.moviedb.MovieVO
import com.linhtetko.themoviebookingapp.persistance.daos.*

@Database(
    entities = [
        MovieVO::class,
        AuthVO::class,
        TokenVO::class,
        GenreVO::class,
        CinemaVO::class,
        SnackVO::class,
        PaymentMethodVO::class,
        CinemaAndTimeSlotVO::class
    ],
    version = 4
)
abstract class MovieBookingDatabase : RoomDatabase() {

    companion object {

        private var INSTANCE: MovieBookingDatabase? = null
        private const val MOVIE_DATABASE = "movie_booking_database"

        fun getInstance(context: Context): MovieBookingDatabase? {
            when (INSTANCE) {
                null -> {
                    INSTANCE =
                        Room.databaseBuilder(
                            context,
                            MovieBookingDatabase::class.java,
                            MOVIE_DATABASE
                        )
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build()
                }
            }
            return INSTANCE
        }
    }

    abstract fun movieDao(): MovieDAO

    abstract fun profileDao(): ProfileDAO

    abstract fun tokenDao(): TokenDAO

    abstract fun genreDao(): GenreDAO

    abstract fun cinemaAndTimeSlotDao(): CinemaAndTimeSlotDAO

    abstract fun paymentMethodDao(): PaymentMethodDAO

    abstract fun snackDao(): SnackDAO
}