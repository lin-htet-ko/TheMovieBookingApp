package com.linhtetko.themoviebookingapp.persistance.typeconverters.cinema

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.linhtetko.themoviebookingapp.data.vos.moviebookingapp.TimeSlotVO

class TimeSlotsTypeConverter {

    @TypeConverter
    fun toString(timeSlots: List<TimeSlotVO>?): String{
        return Gson().toJson(timeSlots)
    }

    @TypeConverter
    fun toTimeSlots(rawJson: String): List<TimeSlotVO>?{
        val typeToken = object : TypeToken<List<TimeSlotVO>>(){}.type
        return Gson().fromJson(rawJson, typeToken)
    }
}