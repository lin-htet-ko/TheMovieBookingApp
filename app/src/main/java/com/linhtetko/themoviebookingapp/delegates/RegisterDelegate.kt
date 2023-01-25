package com.linhtetko.themoviebookingapp.delegates

interface RegisterDelegate {
    fun onClickConfirm(name: String, email: String, phone: Long, password: String)
}