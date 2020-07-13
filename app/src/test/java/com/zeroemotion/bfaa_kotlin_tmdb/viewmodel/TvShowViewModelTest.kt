package com.zeroemotion.bfaa_kotlin_tmdb.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule

class TvShowViewModelTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: TvShowViewModel

    @Before
    fun setUp(){
        viewModel = TvShowViewModel()
    }

    @Test
    fun getTvShow() {
        val tvShows = viewModel.getTvShow()
        assertNotNull(tvShows)
        assertEquals(17, tvShows.size)
    }
}