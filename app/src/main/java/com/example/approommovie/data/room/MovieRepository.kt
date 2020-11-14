package com.example.approommovie.data.room

import com.example.approommovie.data.api.MovieClient
import com.example.approommovie.data.models.Movie

class MovieRepository(private val db: MovieDb) {
    suspend fun getMovies() = MovieClient.api.getPopularMovie()

    suspend fun upsert(article: Movie) = db.getMovieDao().upsert(article)

    fun getSavedMovies() = db.getMovieDao().getAllMovies()

    suspend fun deleteMovie(movie: Movie) = db.getMovieDao().deleteMovie(movie)
}