package com.linhtetko.themoviebookingapp.persistance.typeconverters.movies

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.linhtetko.themoviebookingapp.data.vos.moviedb.ProductionCountriesVO

class ProductionCountriesTypeConverter {

    @TypeConverter
    fun toString(productionCountries: List<ProductionCountriesVO>?): String {
        return Gson().toJson(productionCountries)
    }

    @TypeConverter
    fun toProductionCountries(countries: String): List<ProductionCountriesVO>? {
        val typeToken = object : TypeToken<List<ProductionCountriesVO>?>() {}.type
        return Gson().fromJson(countries, typeToken)
    }
}