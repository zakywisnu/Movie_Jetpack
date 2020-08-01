package com.zeroemotion.bfaa_kotlin_tmdb.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.zeroemotion.bfaa_kotlin_tmdb.ui.movie.MovieViewModel
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule

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