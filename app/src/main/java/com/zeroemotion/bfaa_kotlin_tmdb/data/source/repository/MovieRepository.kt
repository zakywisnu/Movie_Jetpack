package com.zeroemotion.bfaa_kotlin_tmdb.data.source.repository

import androidx.lifecycle.LiveData
import com.zeroemotion.bfaa_kotlin_tmdb.data.model.Movie
import com.zeroemotion.bfaa_kotlin_tmdb.data.model.TvShow

interface MovieRepository {
    fun fetchMovieList(): LiveData<List<Movie>>
    fun fetchTvList(): LiveData<List<TvShow>>
    fun fetchMovieDetail(id: Int): LiveData<Movie>
    fun fetchTvDetail(id: Int): LiveData<TvShow>
}