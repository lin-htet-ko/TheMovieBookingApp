package com.linhtetko.themoviebookingapp.data.vos.moviebookingapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "payment_methods")
data class PaymentMethodVO(
    @ColumnInfo(name = "id")
    @PrimaryKey
    @SerializedName("id")
    val id: Int?,

    @ColumnInfo(name = "description")
    @SerializedName("description")
    val description: String?,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String?,
){
    var isSelect: Boolean = false
}