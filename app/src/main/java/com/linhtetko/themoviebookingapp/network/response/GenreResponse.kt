package com.linhtetko.themoviebookingapp.network.response

import com.google.gson.annotations.SerializedName
import com.linhtetko.themoviebookingapp.data.vos.moviedb.GenreVO

data class GenreResponse(
    @SerializedName("genres")
    val genres: List<GenreVO>
)
