package com.zeroemotion.bfaa_kotlin_tmdb.viewmodel.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.verify
import com.zeroemotion.bfaa_kotlin_tmdb.data.DataDummy
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.remote.RemoteDataSource
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.repository.MovieRepository
import com.zeroemotion.bfaa_kotlin_tmdb.viewmodel.util.LiveDataTestUtil
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.mock

@RunWith(JUnit4::class)
class MovieRepositoryTest {

    private val remote = mock(RemoteDataSource::class.java)
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val dataRepository = FakeMovieRepository(remote)

    private val movieResponse = DataDummy.generateDummyMovies()
    private val movieId = movieResponse[0].id
    private val tvResponse = DataDummy.generateDummyTvShow()
    private val tvId = tvResponse[0].id

    @Test
    fun getMovieList(){
        dataRepository.fetchMovieList()
        val movies = LiveDataTestUtil.getValue(dataRepository.fetchMovieList())
        Assert.assertNotNull(movies.results)
        Assert.assertEquals(movieResponse.size.toLong(),movies.results.size.toLong())
    }
}