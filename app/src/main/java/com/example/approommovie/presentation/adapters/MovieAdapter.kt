package com.example.approommovie.presentation.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.approommovie.R
import com.example.approommovie.data.models.Movie
import com.example.approommovie.presentation.detail.DetailFragment
import com.example.approommovie.utils.POSTER_BASE_URL
import com.example.approommovie.utils.downloadAndSetImage
import kotlinx.android.synthetic.main.main_item.view.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieHolder>() {

    inner class MovieHolder(view : View) : RecyclerView.ViewHolder(view)

    private val differCallback = object : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        return MovieHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.main_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = differ.currentList.size

    private var onItemClickListener: ((Movie) -> Unit)? = null

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val movie = differ.currentList[position]
        holder.itemView.apply {
            val moviePosterURL = POSTER_BASE_URL + movie.posterPath
            poster_imageView.downloadAndSetImage(moviePosterURL)
            title_textView.text = movie.title
            overview_textView.text = movie.overview

            val bundle = Bundle()
            bundle.putString("poster", moviePosterURL)
            val detailMovieFragment = DetailFragment()
            detailMovieFragment.arguments = bundle

            setOnClickListener {
                onItemClickListener?.let { it(movie) }
            }
        }
    }

    fun setOnItemClickListener(listener: (Movie) -> Unit) {
        onItemClickListener = listener
    }
}