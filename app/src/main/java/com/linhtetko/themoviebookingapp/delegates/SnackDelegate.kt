package com.linhtetko.themoviebookingapp.delegates

interface SnackDelegate {
    fun onAddSnack(snackId: Int)
    fun onReduceSnack(snackId: Int)
}