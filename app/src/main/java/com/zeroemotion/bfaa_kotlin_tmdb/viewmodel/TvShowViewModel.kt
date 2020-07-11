package com.zeroemotion.bfaa_kotlin_tmdb.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zeroemotion.bfaa_kotlin_tmdb.model.DataDummy
import com.zeroemotion.bfaa_kotlin_tmdb.model.TvShow

class TvShowViewModel : ViewModel(){

    fun getTvShow(): ArrayList<TvShow> = DataDummy.generateDummyTvShow()
}