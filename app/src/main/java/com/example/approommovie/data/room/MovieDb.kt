package com.example.approommovie.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.approommovie.data.models.Movie

@Database(entities = [Movie::class], version = 1)
abstract class MovieDb: RoomDatabase(){
    abstract fun getMovieDao(): MovieDao

    companion object {
        @Volatile
        private var instance: MovieDb? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                MovieDb::class.java,
                "movie_db.db"
            ).build()
    }
}