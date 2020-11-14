package com.example.approommovie.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.approommovie.data.models.Movie
import com.example.approommovie.data.room.MovieRepository
import kotlinx.coroutines.launch

class FavoritesViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun saveMovie(movie: Movie) = viewModelScope.launch {
        movieRepository.upsert(movie)
    }

    fun getSavedMovies() = movieRepository.getSavedMovies()

    fun deleteMovie(movie: Movie) = viewModelScope.launch {
        movieRepository.deleteMovie(movie)
    }
}