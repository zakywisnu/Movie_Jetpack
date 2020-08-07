package com.zeroemotion.bfaa_kotlin_tmdb.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.zeroemotion.bfaa_kotlin_tmdb.data.model.Movie
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.repository.MovieRepository

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private fun getRepo() = movieRepository.fetchMovieList()
    fun getMovie(): LiveData<List<Movie>> = getRepo()

}