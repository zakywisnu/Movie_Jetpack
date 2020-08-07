package com.zeroemotion.bfaa_kotlin_tmdb.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.zeroemotion.bfaa_kotlin_tmdb.data.model.Movie
import com.zeroemotion.bfaa_kotlin_tmdb.data.model.TvShow
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.repository.MovieRepository

class DetailViewModel(private val movieRepository: MovieRepository, private val id: Int) :
    ViewModel() {

    private fun getMovieRepo() = movieRepository.fetchMovieDetail(id)
    fun getMovieDetail(): LiveData<Movie> = getMovieRepo()

    private fun getTvRepo() = movieRepository.fetchTvDetail(id)
    fun getTvDetail(): LiveData<TvShow> = getTvRepo()

}