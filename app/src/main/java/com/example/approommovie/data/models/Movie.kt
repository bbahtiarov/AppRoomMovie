package com.example.approommovie.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "movie_table")
data class Movie(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Int,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("title")
    var title: String,
    @SerializedName("vote_average")
    var voteAverage: String,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("overview")
    val overview: String,
    var isFavourite: Boolean = false
) :Serializable