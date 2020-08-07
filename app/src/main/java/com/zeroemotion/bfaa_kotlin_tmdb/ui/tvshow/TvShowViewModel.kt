package com.zeroemotion.bfaa_kotlin_tmdb.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.zeroemotion.bfaa_kotlin_tmdb.data.model.TvShow
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.repository.MovieRepository

class TvShowViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private fun getRepo() = movieRepository.fetchTvList()
    fun getTvs(): LiveData<List<TvShow>> = getRepo()

}