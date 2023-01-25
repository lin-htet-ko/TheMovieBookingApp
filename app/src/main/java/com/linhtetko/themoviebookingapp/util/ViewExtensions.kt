package com.linhtetko.themoviebookingapp.util

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar

fun View.showSnackBar(message: String, duration: Int = Snackbar.LENGTH_LONG) {
    Snackbar.make(this, message, duration).show()
}

fun ImageView.loadImageFromMBA(url: String) {
    Glide.with(this.context).load("$MBA_BASE_URL$url").into(this)
}

fun ImageView.loadImageFromMDB(url: String) {
    Glide.with(this.context).load("$MDB_IMAGE_BASE_URL$url").into(this)
}

fun ImageView.loadNormalImage(url: String) {
    Glide.with(this.context).load(url).into(this)
}