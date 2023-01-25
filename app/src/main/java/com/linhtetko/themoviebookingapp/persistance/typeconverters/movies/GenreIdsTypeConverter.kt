package com.linhtetko.themoviebookingapp.persistance.typeconverters.movies

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreIdsTypeConverter {

    @TypeConverter
    fun toString(genreIds: List<Int>?): String {
        return Gson().toJson(genreIds)
    }

    @TypeConverter
    fun toGenreIds(genreIdsString: String): List<Int>?{
        val typeToken = object : TypeToken<List<Int>?>() {}.type
        return Gson().fromJson(genreIdsString, typeToken)
    }
}