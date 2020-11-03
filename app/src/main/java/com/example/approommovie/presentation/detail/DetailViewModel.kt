package com.example.approommovie.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.approommovie.data.models.Movie
import com.example.approommovie.data.room.MovieRepository
import kotlinx.coroutines.launch

class DetailViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    fun saveMovie(movie: Movie) = viewModelScope.launch {
        movieRepository.upsert(movie)
    }
}