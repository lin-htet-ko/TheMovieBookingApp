package com.linhtetko.themoviebookingapp.delegates

import com.linhtetko.themoviebookingapp.data.vos.moviebookingapp.DateTimeVO

interface DateDelegate {
    fun onDateViewClick(dateTimeId: Long)
}