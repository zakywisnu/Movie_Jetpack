package com.zeroemotion.bfaa_kotlin_tmdb.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.local.entity.MovieEntity
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.repository.MovieRepository
import com.zeroemotion.bfaa_kotlin_tmdb.vo.Resource

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private fun getRepo() = movieRepository.getMovieList()
    fun getMovie(): LiveData<Resource<PagedList<MovieEntity>>> = getRepo()

}