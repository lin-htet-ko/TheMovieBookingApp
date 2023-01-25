package com.linhtetko.themoviebookingapp.data.vos.moviebookingapp

data class DateTimeVO(
    val date: Int,
    val dateText: String,
    val dateFormat: String,
    var isSelect: Boolean = false,
    var id: Long = System.currentTimeMillis()
)
