package com.zeroemotion.bfaa_kotlin_tmdb.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: Int?,
    val title: String?,
    val director: String?,
    val overview: String?,
    val posterPath: Int?,
    val releaseDate: String?,
    val voteAverage: String?
//    val backdropPath: String?,
//    val mediaType: String?
): Parcelable