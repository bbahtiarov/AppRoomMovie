package com.example.approommovie.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.approommovie.data.models.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MovieDb: RoomDatabase(){
    abstract fun getMovieDao(): MovieDao
}