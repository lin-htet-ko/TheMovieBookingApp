package com.linhtetko.themoviebookingapp.network.response

import com.google.gson.annotations.SerializedName
import com.linhtetko.themoviebookingapp.data.vos.moviebookingapp.PaymentMethodVO

data class PaymentMethodResponse(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: List<PaymentMethodVO>?,
)