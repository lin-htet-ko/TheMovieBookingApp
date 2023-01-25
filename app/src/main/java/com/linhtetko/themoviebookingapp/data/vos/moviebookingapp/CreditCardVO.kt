package com.linhtetko.themoviebookingapp.data.vos.moviebookingapp

import alirezat775.lib.carouselview.CarouselModel
import com.google.gson.annotations.SerializedName

data class CreditCardVO(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("card_holder")
    val cardHolder: String?,
    @SerializedName("card_number")
    val cardNumber: String?,
    @SerializedName("expiration_date")
    val expirationDate: String?,
    @SerializedName("card_type")
    val cardType: String?
): CarouselModel()
