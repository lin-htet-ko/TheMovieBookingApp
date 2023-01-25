package com.linhtetko.themoviebookingapp.data.vos.moviedb

import com.google.gson.annotations.SerializedName

data class SpokenLanguagesVO(
    @SerializedName("english_name")
    val englishName: String,
    @SerializedName("iso_639_1")
    val iso: String,
    @SerializedName("name")
    val name: String
)
