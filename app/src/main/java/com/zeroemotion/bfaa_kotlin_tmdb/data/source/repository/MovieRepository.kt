package com.zeroemotion.bfaa_kotlin_tmdb.data.source.repository

import androidx.lifecycle.LiveData
import com.zeroemotion.bfaa_kotlin_tmdb.data.model.Movie
import com.zeroemotion.bfaa_kotlin_tmdb.data.model.Response
import com.zeroemotion.bfaa_kotlin_tmdb.data.model.TvShow

interface MovieRepository {
    fun fetchMovieList(): LiveData<Response<Movie>>
    fun fetchTvList(): LiveData<Response<TvShow>>
    fun fetchMovieDetail(id: Int): LiveData<Movie>
    fun fetchTvDetail(id: Int): LiveData<TvShow>
    fun getNetworkState(): LiveData<NetworkState>
}