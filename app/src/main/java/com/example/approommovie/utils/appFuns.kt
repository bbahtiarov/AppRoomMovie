package com.example.approommovie.utils

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.approommovie.MovieApplication
import com.example.approommovie.R
import com.google.android.material.snackbar.Snackbar

fun ImageView.downloadAndSetImage(url: String) {
    Glide
        .with(MovieApplication().applicationContext)
        .load(url)
        .centerCrop()
        .placeholder(R.drawable.placeholder)
        .into(this)
}

fun showSnackbar(view: View, text:String) {
    Snackbar.make(view, text, Snackbar.LENGTH_SHORT).show()
}

