package com.zeroemotion.bfaa_kotlin_tmdb.data.source.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.zeroemotion.bfaa_kotlin_tmdb.data.model.Movie
import com.zeroemotion.bfaa_kotlin_tmdb.data.model.TvShow
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.local.entity.MovieEntity
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.local.entity.TvShowEntity
import com.zeroemotion.bfaa_kotlin_tmdb.vo.Resource

interface MovieRepository {
    fun getMovieList(): LiveData<Resource<PagedList<MovieEntity>>>
    fun getTvList(): LiveData<Resource<PagedList<TvShowEntity>>>
    fun getMovieDetail(id: Int): LiveData<MovieEntity>
    fun getTvDetail(id: Int): LiveData<TvShowEntity>
    fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>>
    fun getFavoriteTvShow(): LiveData<PagedList<TvShowEntity>>
    fun setFavoriteMovie(movie: MovieEntity, state:Boolean)
    fun setFavoriteTvShow(tvs: TvShowEntity, state: Boolean)
}