package com.linhtetko.themoviebookingapp.network.dataagents

import com.linhtetko.themoviebookingapp.data.vos.moviebookingapp.*
import com.linhtetko.themoviebookingapp.network.response.CheckoutResponse
import com.linhtetko.themoviebookingapp.network.response.TimeSlotResponse
import com.linhtetko.themoviebookingapp.util.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.Header
import retrofit2.http.Query

interface MovieBookingAppDataAgent {

    fun registerWithEmail(
        name: String,
        email: String,
        phone: Long,
        password: String,
        googleAccessToken: String? = null,
        facebookAccessToken: String? = null,
        onSuccess: (Pair<AuthVO?, String?>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun loginWithEmail(
        email: String,
        password: String,
        onSuccess: (Pair<AuthVO?, String?>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getProfile(
        token: String,
        onSuccess: (AuthVO?) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getTimeSlots(
        token: String,
        movieId: String?,
        date: String,
        onSuccess: (List<CinemaVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getMovieSeats(
        token: String,
        cinemaDayTimeSlotId: String,
        bookingDate: String,
        onSuccess: (List<List<SeatVO>>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getSnackList(
        token: String,
        onSuccess: (List<SnackVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getPaymentMethods(
        token: String,
        onSuccess: (List<PaymentMethodVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun postCreditCard(
        token: String,
        cardNumber: Int,
        cardHolder: String,
        expirationDate: String,
        cvc: Int,
        onSuccess: (List<CreditCardVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun postCheckout(
        token: String,
        checkout: CheckoutRequestVO,
        onSuccess: (CheckoutVO?) -> Unit,
        onFailure: (String) -> Unit
    )

    fun logout(
        token: String,
        onSuccess: (String) -> Unit,
        onFailure: (String) -> Unit
    )

}