package com.linhtetko.themoviebookingapp.data.vos.moviebookingapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "cinema_and_timeslots")
@TypeConverters(
    CinemasTypeConverter::class
)
data class CinemaAndTimeSlotVO(

    @ColumnInfo(name = "date")
    @PrimaryKey
    val date: String = "",

    @ColumnInfo(name = "time_slots")
    val cinemas: List<CinemaVO>?
)
