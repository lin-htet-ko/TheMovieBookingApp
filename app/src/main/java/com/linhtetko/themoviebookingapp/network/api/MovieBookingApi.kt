package com.linhtetko.themoviebookingapp.network.api

import com.linhtetko.themoviebookingapp.data.vos.moviebookingapp.CheckoutRequestVO
import com.linhtetko.themoviebookingapp.network.response.*
import com.linhtetko.themoviebookingapp.util.*
import retrofit2.Call
import retrofit2.http.*

interface MovieBookingApi {

    @POST(MBA_REGISTER_EMAIL)
    @FormUrlEncoded
    fun registerWithEmail(
        @Field(PARAM_NAME) name: String,
        @Field(PARAM_EMAIL) email: String,
        @Field(PARAM_PHONE) phone: Long,
        @Field(PARAM_PASSWORD) password: String,
        @Field(PARAM_GOOGLE_ACCESS_TOKEN) googleAccessToken: String? = null,
        @Field(PARAM_FACEBOOK_ACCESS_TOKEN) facebookAccessToken: String? = null
    ): Call<AuthResponse>

    @POST(MBA_LOGIN_EMAIL)
    @FormUrlEncoded
    fun loginWithEmail(
        @Field(PARAM_EMAIL) email: String,
        @Field(PARAM_PASSWORD) password: String
    ): Call<AuthResponse>

    @GET(MBA_GET_PROFILE)
    fun getProfile(
        @Header(HEADER_AUTHORIZATION) token: String
    ): Call<AuthResponse>

    @GET(MBA_GET_DATE_TIME_SLOT)
    fun getDateTimeSlot(
        @Header(HEADER_AUTHORIZATION) token: String,
        @Query(PARAM_MOVIE_ID) movieId: String? = null,
        @Query(PARAM_DATE) date: String
    ): Call<TimeSlotResponse>

    @GET(MBA_GET_SEATING_PLAN)
    fun getMovieSeats(
        @Header(HEADER_AUTHORIZATION) token: String,
        @Query(PARMA_CINEMA_DAY_TIME_SLOT_ID) cinemaDayTimeSlotId: String,
        @Query(PARAM_BOOKING_DATE) bookingDate: String
    ): Call<PlanSeatResponse>

    @GET(MBB_GET_SNACK_LIST)
    fun getSnackList(
        @Header(HEADER_AUTHORIZATION) token: String,
    ): Call<SnackResponse>

    @GET(MBB_GET_PAYMENT_METHOD)
    fun getPaymentMethods(
        @Header(HEADER_AUTHORIZATION) token: String
    ): Call<PaymentMethodResponse>

    @POST(MBA_GET_POST_CARD)
    @FormUrlEncoded
    fun postCard(
        @Header(HEADER_AUTHORIZATION) token: String,
        @Field(PARAM_CARD_NUMBER) cardNumber: Int,
        @Field(PARAM_CARD_HOLDER) cardHolder: String,
        @Field(PARAM_EXPIRATION_DATE) expirationDate: String,
        @Field(PARAM_CVC) cvc: Int
    ): Call<CreditCardResponse>

    @POST(MBA_GET_CHECKOUT)
    fun postCheckout(
        @Header(HEADER_AUTHORIZATION) token: String,
        @Body checkout: CheckoutRequestVO
    ): Call<CheckoutResponse>

    @POST(MBA_GET_LOGOUT)
    fun logout(
        @Header(HEADER_AUTHORIZATION) token: String
    ): Call<LogoutResponse>
}