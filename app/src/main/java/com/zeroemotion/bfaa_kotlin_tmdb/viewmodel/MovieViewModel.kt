package com.zeroemotion.bfaa_kotlin_tmdb.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zeroemotion.bfaa_kotlin_tmdb.R
import com.zeroemotion.bfaa_kotlin_tmdb.model.DataDummy
import com.zeroemotion.bfaa_kotlin_tmdb.model.Movie

class MovieViewModel : ViewModel() {
    val movie = MutableLiveData<List<Movie>>()
    val movieLoading = MutableLiveData<Boolean>()


    fun fetch() {

        val movies = DataDummy.generateDummyMovies()
        movie.value = movies
        movieLoading.value = false
    }

}