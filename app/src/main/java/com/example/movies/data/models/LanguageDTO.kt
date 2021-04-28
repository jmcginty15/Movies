package com.example.movies.data.models

import com.google.gson.annotations.SerializedName

class LanguageDTO(
    @SerializedName("iso_639_1") val iso6391: String,
    @SerializedName("name") val name: String
)