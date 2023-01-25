package com.linhtetko.themoviebookingapp.data.model

import com.linhtetko.themoviebookingapp.data.vos.moviebookingapp.*
import com.linhtetko.themoviebookingapp.data.vos.moviedb.MovieVO

interface MovieBookingModel {

    fun getToken(content: (token: String?) -> Unit)

    fun getDbProfile(onResult: (AuthVO?) -> Unit)

    fun getDbPaymentMethod(paymentMethodId: Int): PaymentMethodVO?

    fun getDbSnack(snackId: Int): SnackVO?

    fun getDbMovie(movieId: Int): MovieVO?

    fun getDbTimeSlot(date: String, cinemaPosition: Int, timeSlotId: Int): TimeSlotVO?

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
        onSuccess: (AuthVO?) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getTimeSlots(
        movieId: String?,
        date: String,
        onSuccess: (List<CinemaVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getMovieSeats(
        cinemaDayTimeSlotId: String,
        bookingDate: String,
        onSuccess: (List<List<SeatVO>>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getSnackList(
        onSuccess: (List<SnackVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getPaymentMethods(
        onSuccess: (List<PaymentMethodVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun postCreditCard(
        cardNumber: Int,
        cardHolder: String,
        expirationDate: String,
        cvc: Int,
        onSuccess: (List<CreditCardVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun postCheckout(
        checkout: CheckoutRequestVO,
        onSuccess: (CheckoutVO?) -> Unit,
        onFailure: (String) -> Unit
    )

    fun logout(
        onSuccess: (String) -> Unit,
        onFailure: (String) -> Unit
    )
}