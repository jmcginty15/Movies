package com.example.movies.data.models

import com.google.gson.annotations.SerializedName

class ShortCollectionDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("backdrop_path") val backdropPath: String?
)