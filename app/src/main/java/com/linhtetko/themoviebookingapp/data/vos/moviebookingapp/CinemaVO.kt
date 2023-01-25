package com.linhtetko.themoviebookingapp.data.vos.moviebookingapp

import androidx.room.*
import com.google.gson.annotations.SerializedName
import com.linhtetko.themoviebookingapp.persistance.typeconverters.cinema.TimeSlotsTypeConverter

@Entity(tableName = "cinemas")
@TypeConverters(
    TimeSlotsTypeConverter::class
)
data class CinemaVO(
    @ColumnInfo(name = "cinema_id")
    @SerializedName("cinema_id")
    @PrimaryKey
    var cinemaId: Int?,

    @ColumnInfo(name = "cinema_name")
    @SerializedName("cinema")
    val cinema: String?,

    @ColumnInfo(name = "timeslots")
    @SerializedName("timeslots")
    val timeslots: List<TimeSlotVO>?,

){
    var isSelected: Boolean = false
}
