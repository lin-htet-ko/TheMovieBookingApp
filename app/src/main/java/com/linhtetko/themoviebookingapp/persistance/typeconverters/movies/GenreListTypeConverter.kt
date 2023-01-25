package com.linhtetko.themoviebookingapp.persistance.typeconverters.movies

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.linhtetko.themoviebookingapp.data.vos.moviedb.GenreVO

class GenreListTypeConverter {

    @TypeConverter
    fun toString(genres: List<GenreVO>?): String {
        return Gson().toJson(genres)
    }

    @TypeConverter
    fun toGenres(genresString: String): List<GenreVO>? {
        val typeToken = object : TypeToken<List<GenreVO>?>() {}.type
        return Gson().fromJson(genresString, typeToken)
    }
}