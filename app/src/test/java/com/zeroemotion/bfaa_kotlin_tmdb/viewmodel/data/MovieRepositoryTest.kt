package com.zeroemotion.bfaa_kotlin_tmdb.viewmodel.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.nhaarman.mockitokotlin2.*
import com.zeroemotion.bfaa_kotlin_tmdb.data.DataDummy
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.local.LocalDataSource
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.local.entity.MovieEntity
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.local.entity.TvShowEntity
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.remote.RemoteDataSource
import com.zeroemotion.bfaa_kotlin_tmdb.util.AppExecutors
import com.zeroemotion.bfaa_kotlin_tmdb.viewmodel.util.LiveDataTestUtil
import com.zeroemotion.bfaa_kotlin_tmdb.viewmodel.util.PagedListUtil
import com.zeroemotion.bfaa_kotlin_tmdb.vo.Resource
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.Silent::class)
class MovieRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val movieRepository = FakeMovieRepository(remote,local,appExecutors)

    private val movieListResponse = DataDummy.generateDummyMovies()
    private val movieId = movieListResponse[0].id
    private val tvListResponse = DataDummy.generateDummyTvShow()
    private val tvId = tvListResponse[0].id
    private val movieResponse = DataDummy.generateDummyMovies()[0]
    private val tvResponse = DataDummy.generateDummyTvShow()[0]

    @Test
    fun getMovieList() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int,MovieEntity>
        `when`(local.getMovie()).thenReturn(dataSourceFactory)
        movieRepository.getMovieList()

        val movies = Resource.succes(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getMovie()
        Assert.assertNotNull(movies.data)
        Assert.assertEquals(movieListResponse.size.toLong(), movies.data?.size?.toLong())
    }

    @Test
    fun getTvList() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int,TvShowEntity>
        `when`(local.getTvShow()).thenReturn(dataSourceFactory)
        movieRepository.getTvList()

        val tvs = Resource.succes(PagedListUtil.mockPagedList(DataDummy.generateDummyTvShow()))
        verify(local).getTvShow()
        Assert.assertNotNull(tvs.data)
        Assert.assertEquals(tvListResponse.size.toLong(), tvs.data?.size?.toLong())
    }

    @Test
    fun getMovieDetail() {
        val dummyMovie = MutableLiveData<MovieEntity>()
        dummyMovie.value = movieResponse
        `when`(local.getMovieById(movieId)).thenReturn(dummyMovie)

        val movie = LiveDataTestUtil.getValue(movieRepository.getMovieDetail(movieId))
        Assert.assertNotNull(movie)
        Assert.assertEquals(movieResponse.id, movie.id)
    }

    @Test
    fun getTvDetail() {
        val dummyTv = MutableLiveData<TvShowEntity>()
        dummyTv.value = tvResponse
        `when`(local.getTvShowById(tvId)).thenReturn(dummyTv)

        val tvs = LiveDataTestUtil.getValue(movieRepository.getTvDetail(tvId))
        Assert.assertNotNull(tvs)
        Assert.assertEquals(tvResponse.id, tvs.id)
    }

    @Test
    fun getFavoriteMovie(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getFavoriteMovie()).thenReturn(dataSourceFactory)
        movieRepository.getFavoriteMovie()

        val movies = Resource.succes(PagedListUtil.mockPagedList(movieListResponse))
        verify(local).getFavoriteMovie()
        Assert.assertNotNull(movies.data)
        Assert.assertEquals(movieListResponse.size.toLong(), movies.data?.size?.toLong())
    }

    @Test
     fun getFavoriteTvShow(){
         val dataSoureFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
         `when`(local.getFavoriteTvShow()).thenReturn(dataSoureFactory)
         movieRepository.getFavoriteTvShow()

         val tvs = Resource.succes(PagedListUtil.mockPagedList(tvListResponse))
         verify(local).getFavoriteTvShow()
         Assert.assertNotNull(tvs.data)
         Assert.assertEquals(tvListResponse.size.toLong(), tvs.data?.size?.toLong())
     }
}