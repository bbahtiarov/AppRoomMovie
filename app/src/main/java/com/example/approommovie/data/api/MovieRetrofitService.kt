package com.example.approommovie.data.api

import com.example.approommovie.data.models.MovieResponse
import com.example.approommovie.utils.API_KEY
import com.example.approommovie.utils.API_LANGUAGE
import com.example.approommovie.utils.FIRST_PAGE
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieRetrofitService {
    @GET("movie/popular")
    suspend fun getPopularMovie(
        @Query("api_key") apiKey:String = API_KEY,
        @Query("language") language:String = API_LANGUAGE,
        @Query("page") page: Int = FIRST_PAGE
    ) : Response<MovieResponse>
}