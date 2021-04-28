package com.example.movies.data.models

import com.google.gson.annotations.SerializedName

class MovieListDTO(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<ShortMovieDTO>,
    @SerializedName("total_results") val totalResults: Int,
    @SerializedName("total_pages") val totalPages: Int
)