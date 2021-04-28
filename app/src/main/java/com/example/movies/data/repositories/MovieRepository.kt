package com.example.movies.data.repositories

import android.app.Application
import com.example.movies.data.models.MovieDTO
import com.example.movies.data.models.MovieListDTO
import com.example.movies.data.remote.MovieManager

class MovieRepository(application: Application) {
    private val movieManager = MovieManager()

    suspend fun getPopularMovies(): MovieListDTO = movieManager.getPopularMovies()
    suspend fun getMovieById(id: Int): MovieDTO = movieManager.getMovieById(id)
}