package com.linhtetko.themoviebookingapp.data.vos.moviebookingapp
import com.google.gson.annotations.SerializedName


data class CheckoutRequestVO(
    @SerializedName("booking_date")
    val bookingDate: String,
    @SerializedName("card_id")
    val cardId: Int,
    @SerializedName("cinema_day_timeslot_id")
    val cinemaDayTimeslotId: Int,
    @SerializedName("cinema_id")
    val cinemaId: Int,
    @SerializedName("movie_id")
    val movieId: Int,
    @SerializedName("row")
    val row: String,
    @SerializedName("seat_number")
    val seatNumber: String,
    @SerializedName("snacks")
    val snacks: List<SnackRequestVO>,
//    @SerializedName("total_price")
//    val totalPrice: Int
)

data class SnackRequestVO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("quantity")
    var quatity: Int
)
