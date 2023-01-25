package com.linhtetko.themoviebookingapp.network.response

import com.google.gson.annotations.SerializedName
import com.linhtetko.themoviebookingapp.data.vos.moviedb.ActorVO

data class CreditResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("cast")
    val cast: List<ActorVO>?,
    @SerializedName("crew")
    val crew: List<ActorVO>?,
)
