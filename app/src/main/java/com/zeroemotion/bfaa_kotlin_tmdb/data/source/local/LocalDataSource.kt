package com.zeroemotion.bfaa_kotlin_tmdb.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.local.entity.MovieEntity
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.local.entity.TvShowEntity
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.local.room.MovieDao

class LocalDataSource(private val movieDao: MovieDao) {

    fun getMovie(): DataSource.Factory<Int, MovieEntity> = movieDao.getAllMovie()
    fun getTvShow(): DataSource.Factory<Int, TvShowEntity> = movieDao.getAllTvShow()
    fun getMovieById(id: Int): LiveData<MovieEntity> = movieDao.getMovieById(id)
    fun getTvShowById(id: Int): LiveData<TvShowEntity> = movieDao.getTvShowById(id)

    fun getFavoriteMovie(): DataSource.Factory<Int, MovieEntity> = movieDao.getFavoriteMovie()
    fun getFavoriteTvShow(): DataSource.Factory<Int, TvShowEntity> = movieDao.getFavoriteTvShow()

    fun insertMovie(movie: List<MovieEntity>) {
        movieDao.insertMovie(movie)
    }

    fun insertTvShow(tvs: List<TvShowEntity>) {
        movieDao.insertTvShow(tvs)
    }

    fun setFavoriteMovie(movie:MovieEntity, newState: Boolean){
        movie.favorite = newState
        movieDao.updateMovie(movie)
    }

    fun setFavoriteTvShow(tvs: TvShowEntity, newState: Boolean){
        tvs.favorite = newState
        movieDao.updateTvShow(tvs)
    }


}