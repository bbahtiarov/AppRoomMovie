package com.example.approommovie.data.models

import com.google.gson.annotations.SerializedName

data class MovieResponse (
    @SerializedName("results")
    val movies: MutableList<Movie>
)