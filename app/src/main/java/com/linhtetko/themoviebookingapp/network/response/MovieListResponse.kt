package com.linhtetko.themoviebookingapp.network.response

import com.google.gson.annotations.SerializedName
import com.linhtetko.themoviebookingapp.data.vos.moviedb.DateVO
import com.linhtetko.themoviebookingapp.data.vos.moviedb.MovieVO

data class MovieListResponse(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("dates")
    val dates: DateVO?,
    @SerializedName("results")
    val results: List<MovieVO>?
)
