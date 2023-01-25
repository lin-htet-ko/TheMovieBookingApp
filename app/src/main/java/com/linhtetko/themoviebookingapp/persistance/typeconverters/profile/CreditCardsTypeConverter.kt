package com.linhtetko.themoviebookingapp.persistance.typeconverters.profile

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.linhtetko.themoviebookingapp.data.vos.moviebookingapp.CreditCardVO

class CreditCardsTypeConverter {

    @TypeConverter
    fun toString(creditCards: List<CreditCardVO>?): String{
        return Gson().toJson(creditCards)
    }

    @TypeConverter
    fun toCreditCards(rawString: String): List<CreditCardVO>?{
        val typeToken = object : TypeToken<List<CreditCardVO>>(){}.type
        return Gson().fromJson(rawString, typeToken)
    }
}