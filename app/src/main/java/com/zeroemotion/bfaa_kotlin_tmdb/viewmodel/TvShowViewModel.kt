package com.zeroemotion.bfaa_kotlin_tmdb.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zeroemotion.bfaa_kotlin_tmdb.model.DataDummy
import com.zeroemotion.bfaa_kotlin_tmdb.model.TvShow

class TvShowViewModel : ViewModel(){
    val tvShow = MutableLiveData<List<TvShow>>()
    val tvLoading = MutableLiveData<Boolean>()

    fun fetch() {

        val tvs = DataDummy.generateDummyTvShow()
        tvShow.value = tvs
        tvLoading.value = false
    }
}