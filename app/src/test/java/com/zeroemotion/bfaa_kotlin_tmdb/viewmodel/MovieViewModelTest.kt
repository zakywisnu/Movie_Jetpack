package com.zeroemotion.bfaa_kotlin_tmdb.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.zeroemotion.bfaa_kotlin_tmdb.model.Movie
import io.reactivex.Observer
import io.reactivex.observers.TestObserver
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class MovieViewModelTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MovieViewModel



    @Before
    fun setUp(){
        viewModel = MovieViewModel()
    }

    @Test
    fun getTvShow() {
        val movies = viewModel.getMovies()
        assertNotNull(movies)
        assertEquals(16,movies.size)
    }
}