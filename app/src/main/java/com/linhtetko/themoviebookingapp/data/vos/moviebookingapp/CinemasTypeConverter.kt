package com.linhtetko.themoviebookingapp.data.vos.moviebookingapp

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CinemasTypeConverter {

    @TypeConverter
    fun toString(cinemas: List<CinemaVO>?): String{
        return Gson().toJson(cinemas)
    }

    @TypeConverter
    fun toCinemas(jsonString: String): List<CinemaVO>?{
        val typeToken = object : TypeToken<List<CinemaVO>>(){}.type
        return Gson().fromJson(jsonString, typeToken)
    }
}