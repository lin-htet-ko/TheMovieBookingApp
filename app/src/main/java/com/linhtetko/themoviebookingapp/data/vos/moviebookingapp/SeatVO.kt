package com.linhtetko.themoviebookingapp.data.vos.moviebookingapp

import com.google.gson.annotations.SerializedName

data class SeatVO(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("seat_name")
    val seatName: String?,
    @SerializedName("price")
    val price: Int?,
    @SerializedName("symbol")
    val symbol: String?,
    var isSelected: Boolean = false
) {

    fun isSeatAvailable(): Boolean {
        return type == SEAT_AVAILABLE
    }

    fun isSeatTaken(): Boolean {
        return type == SEAT_RESERVED
    }

    fun isSeatText(): Boolean {
        return type == SEAT_TEXT
    }
}

const val SEAT_AVAILABLE = "available"
const val SEAT_RESERVED = "taken"
const val SEAT_SPACE = "space"
const val SEAT_TEXT = "text"