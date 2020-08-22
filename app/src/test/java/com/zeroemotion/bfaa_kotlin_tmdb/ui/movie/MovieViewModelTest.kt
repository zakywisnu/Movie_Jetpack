package com.zeroemotion.bfaa_kotlin_tmdb.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import com.zeroemotion.bfaa_kotlin_tmdb.data.DataDummy
import com.zeroemotion.bfaa_kotlin_tmdb.data.model.Movie
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.local.entity.MovieEntity
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
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.Silent::class)
class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepositoryImpl: MovieRepositoryImpl


    @Mock
    private lateinit var moviePagedList: PagedList<MovieEntity>

    @Before
    fun start() {
        viewModel = MovieViewModel(movieRepositoryImpl)
    }

    @Test
    fun getMovieList() {
        val movie = Resource.succes(moviePagedList)
        `when`(movie.data?.size).thenReturn(20)
        val movies = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movies.value = movie

        `when`(movieRepositoryImpl.getMovieList()).thenReturn(movies)

        coEvery { movieRepositoryImpl.getMovieList() } returns movies

        assertNotNull(viewModel.getMovie())
        assertEquals(viewModel.getMovie(), movie)

        viewModel.getMovie().observeForever{}

    }

}