package com.example.approommovie.presentation.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.approommovie.data.room.MovieRepository
import com.example.approommovie.presentation.detail.DetailViewModel
import com.example.approommovie.presentation.main.MainViewModel

class DetailViewModelProviderFactory (
    private val movieRepository: MovieRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailViewModel(movieRepository) as T
    }
}