package com.example.approommovie.di

import android.content.Context
import androidx.room.Room
import com.example.approommovie.data.room.MovieDb
import com.example.approommovie.data.room.MovieRepository
import com.example.approommovie.presentation.factories.DetailViewModelProviderFactory
import com.example.approommovie.presentation.factories.FavoritesViewModelProviderFactory
import com.example.approommovie.presentation.factories.MainViewModelProviderFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideMovieDatabase(
        @ApplicationContext context:Context
    ) = Room.databaseBuilder(context.applicationContext, MovieDb::class.java, "movie_db").build()

    @Singleton
    @Provides
    fun provideMovieRepo(
        database : MovieDb
    ) = MovieRepository(database)

    @Singleton
    @Provides
    fun provideMainFactory(
        newRepository: MovieRepository
    ) = MainViewModelProviderFactory(newRepository)

    @Singleton
    @Provides
    fun provideDetailFactory(
        newsRepository: MovieRepository
    ) = DetailViewModelProviderFactory(newsRepository)

    @Singleton
    @Provides
    fun provideFavoritesFactory(
        newsRepository: MovieRepository
    ) = FavoritesViewModelProviderFactory(newsRepository)

}