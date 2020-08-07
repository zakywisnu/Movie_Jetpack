package com.zeroemotion.bfaa_kotlin_tmdb.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.zeroemotion.bfaa_kotlin_tmdb.data.DataDummy
import com.zeroemotion.bfaa_kotlin_tmdb.data.model.Movie
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.repository.MovieRepositoryImpl
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {
    private val dummyMovie = DataDummy.generateDummyMovies()
    private val dummyTvs = DataDummy.generateDummyTvShow()
    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepositoryImpl: MovieRepositoryImpl

    @Mock
    private lateinit var observer: Observer<List<Movie>>

    @Before
    fun start() {
        viewModel = MovieViewModel(movieRepositoryImpl)
    }

    @Test
    fun getMovieList() {
        val movie = MutableLiveData<List<Movie>>()
        movie.value = dummyMovie

        `when`(movieRepositoryImpl.fetchMovieList()).thenReturn(movie)

        val listMovie = viewModel.getMovie().value

        verify(movieRepositoryImpl).fetchMovieList()
        assertNotNull(listMovie)
        assertEquals(20, listMovie?.size)

        viewModel.getMovie().observeForever(observer)
        verify(observer).onChanged(dummyMovie)

    }

}