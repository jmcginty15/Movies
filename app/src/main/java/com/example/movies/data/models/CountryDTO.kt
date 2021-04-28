package com.example.movies.data.models

import com.google.gson.annotations.SerializedName

class CountryDTO(
    @SerializedName("iso_3166_1") val iso31661: String,
    @SerializedName("name") val name: String
)