package com.zeroemotion.bfaa_kotlin_tmdb.data.model

import com.google.gson.annotations.SerializedName

data class Response<T>(
    val page: Int,
    val results: ArrayList<T>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)