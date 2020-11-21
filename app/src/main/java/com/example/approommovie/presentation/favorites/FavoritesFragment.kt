package com.example.approommovie.presentation.favorites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.approommovie.R
import com.example.approommovie.presentation.adapters.MovieAdapter
import com.example.approommovie.presentation.factories.FavoritesViewModelProviderFactory
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.favorites_fragment.*
import javax.inject.Inject

@AndroidEntryPoint
class FavoritesFragment : Fragment(R.layout.favorites_fragment) {

    private lateinit var viewModel: FavoritesViewModel
    lateinit var movieAdapter: MovieAdapter

    @Inject
    lateinit var viewModelProviderFactory:FavoritesViewModelProviderFactory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(FavoritesViewModel::class.java)

        setupRecyclerView()

        movieAdapter.setOnItemClickListener {movie ->
            val bundle = Bundle().apply {
                putSerializable("movie", movie)
            }
            findNavController().navigate(R.id.action_favoritesFragment_to_detailFragment, bundle)
        }

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.absoluteAdapterPosition
                val movie = movieAdapter.differ.currentList[position]
                viewModel.deleteMovie(movie)
                Snackbar.make(view, getString(R.string.delete_movie), Snackbar.LENGTH_LONG).apply {
                    setAction("Undo") {
                        viewModel.saveMovie(movie)
                    }
                    show()
                }
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(favorites_recyclerView)
        }

        viewModel.getSavedMovies().observe(viewLifecycleOwner, Observer {  movies ->
            movieAdapter.differ.submitList(movies)
        })
    }

    private fun setupRecyclerView() {
        movieAdapter = MovieAdapter()
        favorites_recyclerView.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}