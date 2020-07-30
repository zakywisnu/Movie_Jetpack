package com.zeroemotion.bfaa_kotlin_tmdb.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.zeroemotion.bfaa_kotlin_tmdb.data.model.Response
import com.zeroemotion.bfaa_kotlin_tmdb.data.model.TvShow
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.repository.MovieDataSource
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.repository.NetworkState

class TvShowViewModel(private val movieDataSource: MovieDataSource) : ViewModel() {

    private fun getRepo() = movieDataSource.fetchTvList()
    fun getTvs(): LiveData<Response<TvShow>> = getRepo()

    val networkState: LiveData<NetworkState> =
        movieDataSource.getNetworkState()
}