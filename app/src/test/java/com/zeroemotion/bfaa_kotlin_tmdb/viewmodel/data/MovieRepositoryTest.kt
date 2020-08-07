package com.zeroemotion.bfaa_kotlin_tmdb.viewmodel.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import com.zeroemotion.bfaa_kotlin_tmdb.data.DataDummy
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.remote.RemoteDataSource
import com.zeroemotion.bfaa_kotlin_tmdb.viewmodel.util.LiveDataTestUtil
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val movieRepository = FakeMovieRepository(remote)

    private val movieListResponse = DataDummy.generateDummyMovies()
    private val movieId = movieListResponse[0].id
    private val tvListResponse = DataDummy.generateDummyTvShow()
    private val tvId = tvListResponse[0].id
    private val movieResponse = DataDummy.generateDummyMovies()[0]
    private val tvResponse = DataDummy.generateDummyTvShow()[0]

    @Test
    fun getMovieList() {
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[0] as RemoteDataSource.LoadPopularMovies).onAllMoviesReceived(
                    movieListResponse
                )
                null
            }.`when`(remote).getMovieList(any())
        }
        val movies = LiveDataTestUtil.getValue(movieRepository.fetchMovieList())
        runBlocking {
            verify(remote).getMovieList(any())
        }
        Assert.assertNotNull(movies)
        Assert.assertEquals(movieListResponse.size.toLong(), movies.size.toLong())
    }

    @Test
    fun getTvList() {
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[0] as RemoteDataSource.LoadPopularTvShow).onAllTvShowsReceived(
                    tvListResponse
                )
                null
            }.`when`(remote).getTvList(any())
        }
        val tvs = LiveDataTestUtil.getValue(movieRepository.fetchTvList())
        runBlocking {
            verify(remote).getTvList(any())
        }
        Assert.assertNotNull(tvs)
        Assert.assertEquals(tvListResponse.size.toLong(), tvs.size.toLong())
    }

    @Test
    fun getMovieDetail() {
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[1] as RemoteDataSource.LoadMovieDetailCallback).onMovieDetailReceived(
                    movieResponse
                )
                null
            }.`when`(remote).getMovieDetail(eq(movieId), any())
        }

        val movie = LiveDataTestUtil.getValue(movieRepository.fetchMovieDetail(movieId))

        runBlocking {
            verify(remote).getMovieDetail(eq(movieId), any())
        }
        Assert.assertNotNull(movie)
        Assert.assertEquals(movieResponse.id, movie.id)
    }

    @Test
    fun getTvDetail() {
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[1] as RemoteDataSource.LoadTvShowDetailCallback).onTvShowDetailReceived(
                    tvResponse
                )
                null
            }.`when`(remote).getTvDetail(eq(tvId), any())
        }
        val tv = LiveDataTestUtil.getValue(movieRepository.fetchTvDetail(tvId))
        runBlocking {
            verify(remote).getTvDetail(eq(tvId), any())
        }
        Assert.assertNotNull(tv)
        Assert.assertEquals(tvResponse.id, tv.id)
    }
}