package com.linhtetko.themoviebookingapp.data.vos.moviebookingapp

import com.google.gson.annotations.SerializedName

data class TimeSlotVO(
    @SerializedName("cinema_day_timeslot_id")
    val cinemaDateTimeSlotId: Int?,
    @SerializedName("start_time")
    val startTime: String?,
    var isSelected : Boolean = false
)
