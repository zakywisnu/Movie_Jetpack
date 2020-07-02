package com.zeroemotion.bfaa_kotlin_tmdb.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShow(
    val id: Int?,
    val name: String?,
    val overview: String?,
    val posterPath: Int?,
    val firstAirDate: String?,
    val voteAverage: String?
//    val backdropPath: String?,
//    val mediaType: String?
):Parcelable