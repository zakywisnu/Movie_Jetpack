package com.zeroemotion.bfaa_kotlin_tmdb.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.zeroemotion.bfaa_kotlin_tmdb.data.DataDummy
import com.zeroemotion.bfaa_kotlin_tmdb.data.model.TvShow
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.repository.MovieRepositoryImpl
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {
    private val dummyTvs = DataDummy.generateDummyTvShow()
    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepositoryImpl: MovieRepositoryImpl

    @Mock
    private lateinit var observer: Observer<List<TvShow>>

    @Before
    fun start() {
        viewModel = TvShowViewModel(movieRepositoryImpl)
    }

    @Test
    fun getTvList() {
        val tvs = MutableLiveData<List<TvShow>>()
        tvs.value = dummyTvs

        Mockito.`when`(movieRepositoryImpl.fetchTvList()).thenReturn(tvs)

        val listMovie = viewModel.getTvs().value

        verify(movieRepositoryImpl).fetchTvList()
        assertNotNull(listMovie)
        assertEquals(20, listMovie?.size)

        viewModel.getTvs().observeForever(observer)
        verify(observer).onChanged(dummyTvs)
    }

}