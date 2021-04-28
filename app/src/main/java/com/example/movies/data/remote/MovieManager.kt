package com.example.movies.data.remote

import com.example.movies.data.models.MovieDTO
import com.example.movies.data.models.MovieListDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

class MovieManager {
    private val service: MovieService
    private val retrofit = RetrofitService().providesRetrofitService()

    init {
        service = retrofit.create(MovieService::class.java)
    }

    suspend fun getPopularMovies() = service.getPopularMovies()
    suspend fun getMovieById(id: Int) = service.getMovieById(id)

    interface MovieService {
        @GET("discover/movie")
        suspend fun getPopularMovies(
            @Query("sort_by") sortBy: String = "popularity.desc",
            @Query("api_key") key: String = API_KEY
        ): MovieListDTO

        @GET("movie/{id}")
        suspend fun getMovieById(
            @Path("id") id: Int,
            @Query("api_key") key: String = API_KEY
        ): MovieDTO
    }

    companion object {
        val API_KEY = "676d708173fb6f9dab7dddc6db647b6d"
    }
}