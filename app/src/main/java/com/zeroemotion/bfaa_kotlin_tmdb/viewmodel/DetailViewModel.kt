package com.zeroemotion.bfaa_kotlin_tmdb.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zeroemotion.bfaa_kotlin_tmdb.model.DataDummy
import com.zeroemotion.bfaa_kotlin_tmdb.model.Movie
import com.zeroemotion.bfaa_kotlin_tmdb.model.TvShow

class DetailViewModel: ViewModel() {

    val movieLiveData = MutableLiveData<Movie>()
    val tvLiveData = MutableLiveData<TvShow>()

    fun fetchMovie(id: Int){
        val movie = DataDummy.getMovieById(id)
        movieLiveData.value = movie
    }

    fun fetchTV(id: Int){
        val tv = DataDummy.getTvShowById(id)
        tvLiveData.value = tv
    }
}