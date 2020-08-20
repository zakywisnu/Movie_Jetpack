package com.zeroemotion.bfaa_kotlin_tmdb.ui.favorite.favtvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.local.entity.MovieEntity
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.local.entity.TvShowEntity
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.repository.MovieRepository

class FavoriteTvShowViewModel (private val movieRepository: MovieRepository): ViewModel(){
    fun getFavoriteTvShow(): LiveData<PagedList<TvShowEntity>> = movieRepository.getFavoriteTvShow()
}