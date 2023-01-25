package com.linhtetko.themoviebookingapp.persistance.typeconverters.movies

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.linhtetko.themovieapp.data.vos.CollectionVO

class CollectionTypeConverter {

    @TypeConverter
    fun toString(collectionVO: CollectionVO?): String{
        return Gson().toJson(collectionVO)
    }

    @TypeConverter
    fun toCollections(collection: String): CollectionVO?{
        val typeToken = object : TypeToken<CollectionVO?>(){}.type
        return Gson().fromJson(collection, typeToken)
    }
}