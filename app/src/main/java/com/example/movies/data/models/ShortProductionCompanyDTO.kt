package com.example.movies.data.models

import com.google.gson.annotations.SerializedName

class ShortProductionCompanyDTO(
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Int,
    @SerializedName("logo_path") val logoPath: String?,
    @SerializedName("origin_country") val originCountry: String
)