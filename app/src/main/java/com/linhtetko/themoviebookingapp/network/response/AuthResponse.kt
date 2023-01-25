package com.linhtetko.themoviebookingapp.network.response

import com.google.gson.annotations.SerializedName
import com.linhtetko.themoviebookingapp.data.vos.moviebookingapp.AuthVO

data class AuthResponse(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: AuthVO?,
    @SerializedName("token")
    val token: String?,
)
