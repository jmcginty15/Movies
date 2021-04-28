package com.example.movies.data.models

import com.google.gson.annotations.SerializedName

class ShortGenreDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)