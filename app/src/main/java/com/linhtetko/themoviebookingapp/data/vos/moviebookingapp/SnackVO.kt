package com.linhtetko.themoviebookingapp.data.vos.moviebookingapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "snacks")
data class SnackVO(
    @ColumnInfo(name = "id")
    @PrimaryKey
    @SerializedName("id")
    val id: Int?,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String?,

    @ColumnInfo(name = "description")
    @SerializedName("description")
    val description: String?,

    @ColumnInfo(name = "price")
    @SerializedName("price")
    val price: Int?,

    @ColumnInfo(name = "unit_price")
    @SerializedName("unit_price")
    val unitPrice: Int?,

    @ColumnInfo(name = "image")
    @SerializedName("image")
    val image: String?,

    @ColumnInfo(name = "quantity")
    @SerializedName("quantity")
    var quantity: Int = 0,

    @ColumnInfo(name = "total_price")
    @SerializedName("total_price")
    var totalPrice: Int?,
)
