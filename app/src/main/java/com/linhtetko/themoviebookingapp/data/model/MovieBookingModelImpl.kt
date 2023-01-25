package com.linhtetko.themoviebookingapp.data.model

import android.content.Context
import com.linhtetko.themoviebookingapp.data.vos.moviebookingapp.*
import com.linhtetko.themoviebookingapp.data.vos.moviedb.MovieVO
import com.linhtetko.themoviebookingapp.network.dataagents.MovieBookingAppDataAgent
import com.linhtetko.themoviebookingapp.network.dataagents.RetrofitMovieBookingDataAgentImpl
import com.linhtetko.themoviebookingapp.persistance.MovieBookingDatabase

object MovieBookingModelImpl : MovieBookingModel {

    private val movieBookingAppDataAgent: MovieBookingAppDataAgent =
        RetrofitMovieBookingDataAgentImpl

    var mMovieDatabase: MovieBookingDatabase? = null
        private set

    fun initMovieDatabase(context: Context) {
        mMovieDatabase = MovieBookingDatabase.getInstance(context)
        MovieModelImpl.mMovieDatabase = mMovieDatabase
    }

    override fun getToken(content: (token: String?) -> Unit) {
        val profiles = mMovieDatabase?.profileDao()?.getProfile()
        profiles?.lastOrNull()?.token?.let { token ->
            content.invoke(token)
        }
    }

    override fun getDbProfile(onResult: (AuthVO?) -> Unit) {
        val profiles = mMovieDatabase?.profileDao()?.getProfile()
        onResult(profiles?.getOrNull(profiles.size.minus(1)))
    }

    override fun getDbPaymentMethod(paymentMethodId: Int): PaymentMethodVO? {
        return mMovieDatabase?.paymentMethodDao()?.getPaymentMethod(paymentMethodId)
    }

    override fun getDbSnack(snackId: Int): SnackVO? {
        return mMovieDatabase?.snackDao()?.getSnack(snackId)
    }

    override fun getDbMovie(movieId: Int): MovieVO? {
        return mMovieDatabase?.movieDao()?.getMovieById(movieId)
    }

    override fun getDbTimeSlot(date: String, cinemaPosition: Int, timeSlotId: Int): TimeSlotVO? {
        return mMovieDatabase?.cinemaAndTimeSlotDao()?.getCinemaByDate(date)?.cinemas
            ?.getOrNull(cinemaPosition)?.timeslots?.first { it.cinemaDateTimeSlotId == timeSlotId }
    }

    override fun registerWithEmail(
        name: String,
        email: String,
        phone: Long,
        password: String,
        googleAccessToken: String?,
        facebookAccessToken: String?,
        onSuccess: (Pair<AuthVO?, String?>) -> Unit,
        onFailure: (String) -> Unit
    ) {

        movieBookingAppDataAgent.registerWithEmail(
            name,
            email,
            phone,
            password,
            googleAccessToken,
            facebookAccessToken,
            onSuccess = {
                if (it.first != null && it.second != null) {
                    mMovieDatabase?.profileDao()?.insertProfile(it.first!!.copy(token = it.second))
//                    mMovieDatabase?.tokenDao()?.insertToken(TokenVO(token = it.second))
                }
                onSuccess(it)
            },
            onFailure
        )
    }

    override fun loginWithEmail(
        email: String,
        password: String,
        onSuccess: (Pair<AuthVO?, String?>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        movieBookingAppDataAgent.loginWithEmail(email, password, onSuccess = {
            if (it.first != null && it.second != null) {
                mMovieDatabase?.profileDao()?.insertProfile(it.first!!.copy(token = it.second))
//                mMovieDatabase?.tokenDao()?.insertToken(TokenVO(token = it.second))
            }
            onSuccess(it)
        }, onFailure)
    }

    override fun getProfile(
        onSuccess: (AuthVO?) -> Unit,
        onFailure: (String) -> Unit
    ) {

        getDbProfile(onSuccess)
        getToken { token ->
            if (token != null) {
                movieBookingAppDataAgent.getProfile(token, onSuccess = {
                    if (it != null) {
                        mMovieDatabase?.profileDao()?.insertProfile(it.copy(token = token))
                        onSuccess(it)
                    }
                }, onFailure)
            }
        }
    }

    override fun getTimeSlots(
        movieId: String?,
        date: String,
        onSuccess: (List<CinemaVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {


        getToken {
            if (it != null) {
                val cinemaAndDate =
                    mMovieDatabase?.cinemaAndTimeSlotDao()?.getCinemaByDate(date)?.cinemas
                onSuccess(
                    cinemaAndDate ?: emptyList()
                )

                movieBookingAppDataAgent.getTimeSlots(
                    it,
                    movieId,
                    date,
                    onSuccess = { cinemas ->

                        mMovieDatabase?.cinemaAndTimeSlotDao()
                            ?.insertCinemaAndTimeSlots(
                                CinemaAndTimeSlotVO(
                                    date = date,
                                    cinemas = cinemas
                                )
                            )

                        onSuccess(cinemas)
                    },
                    onFailure
                )
            }
        }
    }

    override fun getMovieSeats(
        cinemaDayTimeSlotId: String,
        bookingDate: String,
        onSuccess: (List<List<SeatVO>>) -> Unit,
        onFailure: (String) -> Unit
    ) {

        getToken {
            if (it != null) {
                movieBookingAppDataAgent.getMovieSeats(
                    it,
                    cinemaDayTimeSlotId,
                    bookingDate,
                    onSuccess,
                    onFailure
                )
            }
        }
    }

    override fun getSnackList(
        onSuccess: (List<SnackVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        getToken {
            if (it != null) {

                onSuccess(mMovieDatabase?.snackDao()?.getSnacks() ?: emptyList())

                movieBookingAppDataAgent.getSnackList(it, onSuccess = { snacks ->

                    mMovieDatabase?.snackDao()?.insertSnacks(snacks)
                    onSuccess(snacks)
                }, onFailure)
            }
        }
    }

    override fun getPaymentMethods(
        onSuccess: (List<PaymentMethodVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        getToken {
            if (it != null) {

                onSuccess(mMovieDatabase?.paymentMethodDao()?.getPaymentMethods() ?: emptyList())

                movieBookingAppDataAgent.getPaymentMethods(it, onSuccess = { paymentMethods ->
                    mMovieDatabase?.paymentMethodDao()?.insertPaymentMethods(paymentMethods)
                    onSuccess(paymentMethods)
                }, onFailure)
            }
        }
    }

    override fun postCreditCard(
        cardNumber: Int,
        cardHolder: String,
        expirationDate: String,
        cvc: Int,
        onSuccess: (List<CreditCardVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {

        getDbProfile {
            onSuccess(it?.cards ?: emptyList())
        }

        getToken {
            if (it != null) {
                movieBookingAppDataAgent.postCreditCard(
                    it,
                    cardNumber,
                    cardHolder,
                    expirationDate,
                    cvc,
                    onSuccess,
                    onFailure
                )
            }
        }
    }

    override fun postCheckout(
        checkout: CheckoutRequestVO,
        onSuccess: (CheckoutVO?) -> Unit,
        onFailure: (String) -> Unit
    ) {
        getToken {
            if (it != null) {
                movieBookingAppDataAgent.postCheckout(it, checkout, onSuccess, onFailure)
            }
        }
    }

    override fun logout(onSuccess: (String) -> Unit, onFailure: (String) -> Unit) {
        getToken {
            if (it != null) {
                movieBookingAppDataAgent.logout(
                    token = it,
                    onSuccess = { message ->
                        mMovieDatabase?.profileDao()?.deleteProfile()
//                        mMovieDatabase?.tokenDao()?.deleteTokens()
                        onSuccess(message)
                    },
                    onFailure = onFailure
                )
            }
        }
    }


}