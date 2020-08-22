package com.zeroemotion.bfaa_kotlin_tmdb.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import com.zeroemotion.bfaa_kotlin_tmdb.data.DataDummy
import com.zeroemotion.bfaa_kotlin_tmdb.data.model.TvShow
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.local.entity.TvShowEntity
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.repository.MovieRepositoryImpl
import com.zeroemotion.bfaa_kotlin_tmdb.vo.Resource
import io.mockk.coEvery
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.Silent::class)
class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepositoryImpl: MovieRepositoryImpl

    @Mock
    private lateinit var tvPagedList: PagedList<TvShowEntity>

    @Before
    fun start() {
        viewModel = TvShowViewModel(movieRepositoryImpl)
    }

    @Test
    fun getTvList() {
        val tv = Resource.succes(tvPagedList)
        coEvery { tv.data?.size } returns 20
        val tvs = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        tvs.value = tv

        coEvery { movieRepositoryImpl.getTvList() } returns tvs
        viewModel.getTvs().observeForever{}
        assertNotNull(viewModel.getTvs())
        assertEquals(viewModel.getTvs(), tv)
    }

}