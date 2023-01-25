package com.linhtetko.themoviebookingapp.network.dataagents

import android.util.Log
import com.linhtetko.themoviebookingapp.data.model.MovieBookingModelImpl
import com.linhtetko.themoviebookingapp.data.vos.moviebookingapp.*
import com.linhtetko.themoviebookingapp.network.api.MovieBookingApi
import com.linhtetko.themoviebookingapp.network.response.*
import com.linhtetko.themoviebookingapp.util.MBA_BASE_URL
import com.linhtetko.themoviebookingapp.util.bearer
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitMovieBookingDataAgentImpl : MovieBookingAppDataAgent {

    const val ERR_SOMETHING_WRONG = "Something Wrong"

    var movieBookingApi: MovieBookingApi

    init {
        val client = OkHttpClient.Builder()
            .callTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(MBA_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        movieBookingApi = retrofit.create(MovieBookingApi::class.java)
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
        movieBookingApi.registerWithEmail(
            name,
            email,
            phone,
            password,
            googleAccessToken,
            facebookAccessToken
        ).enqueue(
            object : Callback<AuthResponse> {
                override fun onResponse(
                    call: Call<AuthResponse>,
                    response: Response<AuthResponse>
                ) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        body?.apply {
                            when (code) {
                                in 200..299 -> {
                                    onSuccess(Pair(data, token))
                                }
                                in 400..599 -> {
                                    onFailure(message ?: ERR_SOMETHING_WRONG)
                                }
                            }
                        }
                    } else {
                        onFailure(response.message() ?: ERR_SOMETHING_WRONG)
                    }
                }

                override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                    onFailure(t.message ?: ERR_SOMETHING_WRONG)
                }
            }
        )
    }

    override fun loginWithEmail(
        email: String,
        password: String,
        onSuccess: (Pair<AuthVO?, String?>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        movieBookingApi.loginWithEmail(
            email,
            password,
        ).enqueue(
            object : Callback<AuthResponse> {
                override fun onResponse(
                    call: Call<AuthResponse>,
                    response: Response<AuthResponse>
                ) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        body?.apply {
                            when (code) {
                                in 200..299 -> {
                                    onSuccess(Pair(data, token))
                                }
                                in 400..599 -> {
                                    onFailure(message ?: ERR_SOMETHING_WRONG)
                                }
                            }
                        }
                    } else {
                        onFailure(response.message() ?: ERR_SOMETHING_WRONG)
                    }
                }

                override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                    onFailure(t.message ?: ERR_SOMETHING_WRONG)
                }
            }
        )
    }

    override fun getProfile(
        token: String,
        onSuccess: (AuthVO?) -> Unit,
        onFailure: (String) -> Unit
    ) {
        movieBookingApi.getProfile(token.bearer).enqueue(
            object : Callback<AuthResponse> {
                override fun onResponse(
                    call: Call<AuthResponse>,
                    response: Response<AuthResponse>
                ) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        body?.apply {
                            when (code) {
                                in 200..299 -> {
                                    onSuccess(data)
                                }
                                in 400..599 -> {
                                    onFailure(message ?: ERR_SOMETHING_WRONG)
                                }
                            }
                        }
                    } else {
                        onFailure(response.message() ?: ERR_SOMETHING_WRONG)
                    }
                }

                override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                    onFailure(t.message ?: ERR_SOMETHING_WRONG)
                }
            }
        )
    }

    override fun getTimeSlots(
        token: String,
        movieId: String?,
        date: String,
        onSuccess: (List<CinemaVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        movieBookingApi.getDateTimeSlot(token.bearer, movieId, date).enqueue(
            object : Callback<TimeSlotResponse> {
                override fun onResponse(
                    call: Call<TimeSlotResponse>,
                    response: Response<TimeSlotResponse>
                ) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        body?.apply {
                            when (code) {
                                in 200..299 -> {
                                    onSuccess(data)
                                }
                                in 400..599 -> {
                                    onFailure(message ?: ERR_SOMETHING_WRONG)
                                }
                            }
                        }
                    } else {
                        onFailure(response.message() ?: ERR_SOMETHING_WRONG)
                    }
                }

                override fun onFailure(call: Call<TimeSlotResponse>, t: Throwable) {

                }
            }
        )
    }

    override fun getMovieSeats(
        token: String,
        cinemaDayTimeSlotId: String,
        bookingDate: String,
        onSuccess: (List<List<SeatVO>>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        movieBookingApi.getMovieSeats(
            token.bearer, cinemaDayTimeSlotId, bookingDate
        ).enqueue(object : Callback<PlanSeatResponse> {
            override fun onResponse(
                call: Call<PlanSeatResponse>,
                response: Response<PlanSeatResponse>
            ) {

                if (response.isSuccessful) {
                    val body = response.body()
                    body?.apply {
                        when (code) {
                            in 200..299 -> {
                                onSuccess(data)
                            }
                            in 400..599 -> {
                                onFailure(message ?: ERR_SOMETHING_WRONG)
                            }
                        }
                    }
                } else {
                    onFailure(response.message() ?: ERR_SOMETHING_WRONG)
                }
            }

            override fun onFailure(call: Call<PlanSeatResponse>, t: Throwable) {
                onFailure(t.message ?: ERR_SOMETHING_WRONG)
            }
        })
    }

    override fun getSnackList(
        token: String,
        onSuccess: (List<SnackVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        movieBookingApi.getSnackList(
            token.bearer
        ).enqueue(object : Callback<SnackResponse> {
            override fun onResponse(
                call: Call<SnackResponse>,
                response: Response<SnackResponse>
            ) {

                if (response.isSuccessful) {
                    val body = response.body()
                    body?.apply {
                        when (code) {
                            in 200..299 -> {
                                onSuccess(data ?: listOf())
                            }
                            in 400..599 -> {
                                onFailure(message ?: ERR_SOMETHING_WRONG)
                            }
                        }
                    }
                } else {
                    onFailure(response.message() ?: ERR_SOMETHING_WRONG)
                }
            }

            override fun onFailure(call: Call<SnackResponse>, t: Throwable) {
                onFailure(t.message ?: ERR_SOMETHING_WRONG)
            }
        })
    }

    override fun getPaymentMethods(
        token: String,
        onSuccess: (List<PaymentMethodVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        movieBookingApi.getPaymentMethods(
            token.bearer
        ).enqueue(object : Callback<PaymentMethodResponse> {
            override fun onResponse(
                call: Call<PaymentMethodResponse>,
                response: Response<PaymentMethodResponse>
            ) {

                if (response.isSuccessful) {
                    val body = response.body()
                    body?.apply {
                        when (code) {
                            in 200..299 -> {
                                onSuccess(data ?: listOf())
                            }
                            in 400..599 -> {
                                onFailure(message ?: ERR_SOMETHING_WRONG)
                            }
                        }
                    }
                } else {
                    onFailure(response.message() ?: ERR_SOMETHING_WRONG)
                }
            }

            override fun onFailure(call: Call<PaymentMethodResponse>, t: Throwable) {
                onFailure(t.message ?: ERR_SOMETHING_WRONG)
            }
        })
    }

    override fun postCreditCard(
        token: String,
        cardNumber: Int,
        cardHolder: String,
        expirationDate: String,
        cvc: Int,
        onSuccess: (List<CreditCardVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        movieBookingApi.postCard(
            token.bearer,
            cardNumber,
            cardHolder,
            expirationDate,
            cvc
        ).enqueue(object : Callback<CreditCardResponse> {
            override fun onResponse(
                call: Call<CreditCardResponse>,
                response: Response<CreditCardResponse>
            ) {

                if (response.isSuccessful) {
                    val body = response.body()
                    body?.apply {
                        when (code) {
                            in 200..299 -> {
                                onSuccess(data ?: listOf())
                            }
                            in 400..599 -> {
                                onFailure(message ?: ERR_SOMETHING_WRONG)
                            }
                        }
                    }
                } else {
                    onFailure(response.message() ?: ERR_SOMETHING_WRONG)
                }
            }

            override fun onFailure(call: Call<CreditCardResponse>, t: Throwable) {
                onFailure(t.message ?: ERR_SOMETHING_WRONG)
            }
        })
    }

    override fun postCheckout(
        token: String,
        checkout: CheckoutRequestVO,
        onSuccess: (CheckoutVO?) -> Unit,
        onFailure: (String) -> Unit
    ) {
        movieBookingApi.postCheckout(
            token.bearer,
            checkout
        ).enqueue(object : Callback<CheckoutResponse> {
            override fun onResponse(
                call: Call<CheckoutResponse>,
                response: Response<CheckoutResponse>
            ) {

                if (response.isSuccessful) {
                    val body = response.body()
                    body?.apply {
                        when (code) {
                            in 200..299 -> {
                                onSuccess(data)
                            }
                            in 400..599 -> {
                                onFailure(message ?: ERR_SOMETHING_WRONG)
                            }
                        }
                    }
                } else {
                    onFailure(response.message() ?: ERR_SOMETHING_WRONG)
                }
            }

            override fun onFailure(call: Call<CheckoutResponse>, t: Throwable) {
                onFailure(t.message ?: ERR_SOMETHING_WRONG)
            }
        })
    }

    override fun logout(token: String, onSuccess: (String) -> Unit, onFailure: (String) -> Unit) {
        movieBookingApi.logout(token.bearer).enqueue(
            object : Callback<LogoutResponse>{
                override fun onResponse(
                    call: Call<LogoutResponse>,
                    response: Response<LogoutResponse>
                ) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        body?.apply {
                            when (code) {
                                in 200..299 -> {
                                    onSuccess(message ?: "")
                                }
                                in 400..599 -> {
                                    onFailure(message ?: ERR_SOMETHING_WRONG)
                                }
                            }
                        }
                    } else {
                        onFailure(response.message() ?: ERR_SOMETHING_WRONG)
                    }
                }

                override fun onFailure(call: Call<LogoutResponse>, t: Throwable) {
                    onFailure(t.message ?: ERR_SOMETHING_WRONG)
                }
            }
        )
    }
}