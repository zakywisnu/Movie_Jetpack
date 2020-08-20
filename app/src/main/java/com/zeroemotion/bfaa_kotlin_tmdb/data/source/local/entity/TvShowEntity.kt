package com.zeroemotion.bfaa_kotlin_tmdb.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tvshowentity")
data class TvShowEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "poster_path")
    var posterPath: String,

    @ColumnInfo(name = "first_air_date")
    var firstAirDate: String,

    @ColumnInfo(name = "vote_average")
    var voteAverage: String,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false
)