package com.linhtetko.themoviebookingapp.network.response

import com.google.gson.annotations.SerializedName

data class LogoutResponse(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("message")
    val message: String?
)