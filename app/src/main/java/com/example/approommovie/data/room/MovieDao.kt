package com.example.approommovie.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.approommovie.data.models.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(movie: Movie): Long

    @Query("SELECT * FROM movie_table")
    fun getAllMovies(): LiveData<List<Movie>>

    @Delete
    suspend fun deleteMovie(movie: Movie)

}