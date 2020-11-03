package com.example.approommovie.presentation.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.approommovie.R
import com.example.approommovie.data.models.Movie
import com.example.approommovie.data.room.MovieDb
import com.example.approommovie.data.room.MovieRepository
import com.example.approommovie.presentation.factories.DetailViewModelProviderFactory
import com.example.approommovie.presentation.factories.MainViewModelProviderFactory
import com.example.approommovie.utils.APP_ACTIVITY
import com.example.approommovie.utils.POSTER_BASE_URL
import com.example.approommovie.utils.downloadAndSetImage
import com.example.approommovie.utils.showSnackbar
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.detail_fragment.*

class DetailFragment : Fragment(R.layout.detail_fragment) {

    lateinit var viewModel: DetailViewModel
    private val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val newsRepository = MovieRepository(MovieDb(APP_ACTIVITY))
        val viewModelProviderFactory = DetailViewModelProviderFactory(newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(DetailViewModel::class.java)

        val movie = args.movie
        bindUi(movie)

        like_btn.setOnClickListener {
            viewModel.saveMovie(movie)
            showSnackbar(view, getString(R.string.save_movie))
        }
    }

    private fun bindUi(movie: Movie) {
        movie_title_textView.text = movie.title
        release_date_textView.text = movie.releaseDate
        vote_average_textView.text = movie.voteAverage
        original_language_text_view.text = movie.originalLanguage
        overview_textView.text = movie.overview
        val moviePosterURL = POSTER_BASE_URL + movie.posterPath
        movie_poster_imageView.downloadAndSetImage(moviePosterURL)
    }
}