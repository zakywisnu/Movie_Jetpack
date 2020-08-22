package com.zeroemotion.bfaa_kotlin_tmdb.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.zeroemotion.bfaa_kotlin_tmdb.data.DataDummy
import com.zeroemotion.bfaa_kotlin_tmdb.data.model.Movie
import com.zeroemotion.bfaa_kotlin_tmdb.data.model.TvShow
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.local.entity.MovieEntity
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.local.entity.TvShowEntity
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

@RunWith(MockitoJUnitRunner.Silent::class)
class DetailViewModelTest {

    private val dummyMovie = DataDummy.generateDummyMovies()[0]
    private val movieId = dummyMovie.id
    private val dummyTvs = DataDummy.generateDummyTvShow()[0]
    private val tvId = dummyTvs.id

    private lateinit var viewModelMovie: DetailViewModel
    private lateinit var viewModelTv: DetailViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepositoryImpl: MovieRepositoryImpl

    @Mock
    private lateinit var observerMovie: Observer<MovieEntity>

    @Mock
    private lateinit var observerTvs: Observer<TvShowEntity>

    @Before
    fun start() {
        viewModelMovie = DetailViewModel(movieRepositoryImpl, movieId)
        viewModelTv = DetailViewModel(movieRepositoryImpl, tvId)
    }

    @Test
    fun getMovieDetail() {
        val movie = MutableLiveData<MovieEntity>()
        movie.value = dummyMovie

        `when`(movieRepositoryImpl.getMovieDetail(movieId)).thenReturn(movie)

        val movieData = viewModelMovie.getMovieDetail.value

        assertNotNull(movieData)
        assertEquals(dummyMovie.id, movieData?.id)
        assertEquals(dummyMovie.title, movieData?.title)
        assertEquals(dummyMovie.overview, movieData?.overview)
        assertEquals(dummyMovie.posterPath, movieData?.posterPath)
        assertEquals(dummyMovie.voteAverage, movieData?.voteAverage)
        assertEquals(dummyMovie.releaseDate, movieData?.releaseDate)

        viewModelMovie.getMovieDetail.observeForever(observerMovie)
        verify(observerMovie).onChanged(dummyMovie)
    }

    @Test
    fun getTvDetail() {
        val tv = MutableLiveData<TvShowEntity>()
        tv.value = dummyTvs
        `when`(movieRepositoryImpl.getTvDetail(tvId)).thenReturn(tv)

        val tvData = viewModelTv.getTvDetail.value

        assertNotNull(tvData)
        assertEquals(dummyTvs.id, tvData?.id)
        assertEquals(dummyTvs.name, tvData?.name)
        assertEquals(dummyTvs.posterPath, tvData?.posterPath)
        assertEquals(dummyTvs.firstAirDate, tvData?.firstAirDate)
        assertEquals(dummyTvs.overview, tvData?.overview)
        assertEquals(dummyTvs.voteAverage, tvData?.voteAverage)

        viewModelTv.getTvDetail.observeForever(observerTvs)
        verify(observerTvs).onChanged(dummyTvs)
    }

}