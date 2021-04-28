package com.example.movies.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.movies.data.models.MovieDTO
import com.example.movies.data.models.MovieListDTO
import com.example.movies.data.repositories.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val movieRepository: MovieRepository by lazy {
        MovieRepository(application)
    }

    private val _movieList: MutableLiveData<MovieListDTO> = MutableLiveData()
    val movieList: LiveData<MovieListDTO> get() = _movieList

    private val _movie: MutableLiveData<MovieDTO> = MutableLiveData()
    val movie: LiveData<MovieDTO> get() = _movie

    private val _loadingState: MutableLiveData<String> = MutableLiveData()
    val loadingState: LiveData<String> get() = _loadingState

    init {
        getPopularMovies()
    }

    private fun getPopularMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) { _loadingState.value = "Loading..." }
            try {
                _movieList.postValue(movieRepository.getPopularMovies())
                _loadingState.value = "loaded"
            } catch (e: Exception) {
                _loadingState.postValue("An error has occurred.")
            }
        }
    }

    fun getMovieById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) { _loadingState.value = "Loading..." }
            try {
                _movie.postValue(movieRepository.getMovieById(id))
                _loadingState.value = "loaded"
            } catch (e: Exception) {
                _loadingState.postValue("An error has occurred.")
            }
        }
    }

    fun setLoadingState(state: String) {
        _loadingState.value = state
    }
}