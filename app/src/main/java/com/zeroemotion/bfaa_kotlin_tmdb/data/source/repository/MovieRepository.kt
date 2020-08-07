package com.zeroemotion.bfaa_kotlin_tmdb.data.source.repository

import androidx.lifecycle.LiveData
import com.zeroemotion.bfaa_kotlin_tmdb.data.model.Movie
import com.zeroemotion.bfaa_kotlin_tmdb.data.model.Response
import com.zeroemotion.bfaa_kotlin_tmdb.data.model.TvShow

interface MovieRepository {
    fun fetchMovieList(): LiveData<NetworkState<List<Movie>>>
    fun fetchTvList(): LiveData<NetworkState<List<TvShow>>>
    fun fetchMovieDetail(id: Int): LiveData<NetworkState<Movie>>
    fun fetchTvDetail(id: Int): LiveData<NetworkState<TvShow>>
}