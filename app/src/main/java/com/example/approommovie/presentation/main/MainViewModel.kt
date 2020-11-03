package com.example.approommovie.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.approommovie.data.models.MovieResponse
import com.example.approommovie.data.room.MovieRepository
import com.example.approommovie.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    val movies: MutableLiveData<Resource<MovieResponse>> = MutableLiveData()
    var movieResponse: MovieResponse? = null

    init {
        getMovies()
    }

    private fun getMovies() = viewModelScope.launch {
        movies.postValue(Resource.Loading())
        val response = movieRepository.getMovies()
        movies.postValue(handleBreakingNewsResponse(response))
    }

    private fun handleBreakingNewsResponse(response: Response<MovieResponse>) : Resource<MovieResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                if(movieResponse == null) {
                    movieResponse = resultResponse
                } else {
                    val oldMovies = movieResponse?.movies
                    val newMovies = resultResponse.movies
                    oldMovies?.addAll(newMovies)
                }
                return Resource.Success(movieResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}