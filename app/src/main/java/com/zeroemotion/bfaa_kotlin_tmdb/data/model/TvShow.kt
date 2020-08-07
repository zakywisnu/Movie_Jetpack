package com.zeroemotion.bfaa_kotlin_tmdb.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShow(
    val id: Int,
    val name: String,
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("first_air_date")
    val firstAirDate: String,
    @SerializedName("vote_average")
    val voteAverage: String
) : Parcelable