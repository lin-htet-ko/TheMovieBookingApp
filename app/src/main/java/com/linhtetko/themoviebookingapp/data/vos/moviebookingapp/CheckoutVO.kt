package com.linhtetko.themoviebookingapp.data.vos.moviebookingapp
import com.google.gson.annotations.SerializedName


data class CheckoutVO(
    @SerializedName("booking_date")
    val bookingDate: String?,
    @SerializedName("booking_no")
    val bookingNo: String?,
    @SerializedName("cinema_id")
    val cinemaId: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("movie_id")
    val movieId: Int?,
    @SerializedName("qr_code")
    val qrCode: String?,
    @SerializedName("row")
    val row: String?,
    @SerializedName("seat")
    val seat: String?,
    @SerializedName("snacks")
    val snacks: List<SnackVO>?,
    @SerializedName("timeslot")
    val timeslot: TimeSlotVO?,
    @SerializedName("total")
    val total: String?,
    @SerializedName("total_seat")
    val totalSeat: Int?,
    @SerializedName("username")
    val username: String?
)
