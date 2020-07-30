package com.zeroemotion.bfaa_kotlin_tmdb.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.zeroemotion.bfaa_kotlin_tmdb.data.model.Movie
import com.zeroemotion.bfaa_kotlin_tmdb.data.model.TvShow
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.repository.MovieDataSource

class DetailViewModel(private val movieDataSource: MovieDataSource, private val id: Int) :
    ViewModel() {

    private fun getMovieRepo() = movieDataSource.fetchMovieDetail(id)
    fun getMovieDetail(): LiveData<Movie> = getMovieRepo()

    private fun getTvRepo() = movieDataSource.fetchTvDetail(id)
    fun getTvDetail(): LiveData<TvShow> = getTvRepo()

}