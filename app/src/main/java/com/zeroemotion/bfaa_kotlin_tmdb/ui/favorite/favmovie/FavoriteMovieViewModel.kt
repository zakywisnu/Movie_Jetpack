package com.zeroemotion.bfaa_kotlin_tmdb.ui.favorite.favmovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.local.entity.MovieEntity
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.repository.MovieRepository

class FavoriteMovieViewModel (private val movieRepository: MovieRepository): ViewModel(){
    fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>> = movieRepository.getFavoriteMovie()

}