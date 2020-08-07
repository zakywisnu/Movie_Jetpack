package com.zeroemotion.bfaa_kotlin_tmdb.viewmodel.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.zeroemotion.bfaa_kotlin_tmdb.data.DataDummy
import com.zeroemotion.bfaa_kotlin_tmdb.data.model.Movie
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.remote.RemoteDataSource
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.repository.MovieRepository
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.repository.MovieRepositoryImpl
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.repository.NetworkState
import com.zeroemotion.bfaa_kotlin_tmdb.ui.movie.MovieViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieRepositoryTest {

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val movieRepository = FakeMovieRepository(remote)

    private val movieResponse = DataDummy.generateDummyMovies()
    private val movieId = movieResponse[0].id
    private val tvResponse = DataDummy.generateDummyTvShow()
    private val tvId = tvResponse[0].id

    @Test
    fun getMovieList() {
    }
}