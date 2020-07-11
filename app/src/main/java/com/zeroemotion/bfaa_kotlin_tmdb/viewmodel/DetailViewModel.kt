package com.zeroemotion.bfaa_kotlin_tmdb.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zeroemotion.bfaa_kotlin_tmdb.model.DataDummy
import com.zeroemotion.bfaa_kotlin_tmdb.model.Movie
import com.zeroemotion.bfaa_kotlin_tmdb.model.TvShow

class DetailViewModel: ViewModel() {


    fun getDetailMovie(id: Int?): Movie? = DataDummy.getMovieById(id)
    fun getDetailTv(tvid: Int?): TvShow? = DataDummy.getTvShowById(tvid)




}