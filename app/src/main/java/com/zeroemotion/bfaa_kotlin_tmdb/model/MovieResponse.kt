package com.zeroemotion.bfaa_kotlin_tmdb.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("results")
    val results: ArrayList<Movie>?,

    @SerializedName("total_results")
    val totalResults: Int?
)