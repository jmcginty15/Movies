package com.example.movies.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.data.models.ShortMovieDTO
import com.example.movies.databinding.MovieItemBinding

class MovieListAdapter(private val listener: (movieId: Int) -> Unit) :
    RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {
    private var movieList: List<ShortMovieDTO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(
            MovieItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) = with(holder) {
        val movie = movieList[position]
        loadData(movie)
        movieItemLayout.setOnClickListener { listener(movie.id) }
    }

    override fun getItemCount(): Int = movieList.size

    class MovieViewHolder(private val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val movieItemLayout = binding.movieItemLayout
        fun loadData(movie: ShortMovieDTO) {
            binding.title.text = movie.title
            binding.releaseYear.text = getYear(movie.releaseDate)
        }

        private fun getYear(date: String): String = date.slice(0..3)
    }

    fun addData(movies: List<ShortMovieDTO>) {
        movieList = movies
        notifyDataSetChanged()
    }
}