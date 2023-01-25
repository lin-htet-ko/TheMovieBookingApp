package com.linhtetko.themoviebookingapp.data.vos.moviebookingapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.linhtetko.themoviebookingapp.persistance.typeconverters.profile.CreditCardsTypeConverter

@Entity(
    tableName = "profile"
)
@TypeConverters(
    CreditCardsTypeConverter::class
)
data class AuthVO(
    @SerializedName("id")
    @ColumnInfo(name = "id")
    @PrimaryKey
    val id: Int?,

    @SerializedName("name")
    @ColumnInfo(name = "name")
    val name: String?,

    @ColumnInfo(name = "email")
    @SerializedName("email")
    val email: String?,

    @ColumnInfo(name = "phone")
    @SerializedName("phone")
    val phone: Long?,

    @ColumnInfo(name = "total_expense")
    @SerializedName("total_expense")
    val totalExpense: Int?,

    @ColumnInfo(name = "profile_image")
    @SerializedName("profile_image")
    val profileImage: String?,

    @ColumnInfo(name = "cards")
    @SerializedName("cards")
    val cards: List<CreditCardVO>?,

    @ColumnInfo(name = "token")
    val token: String?
)
