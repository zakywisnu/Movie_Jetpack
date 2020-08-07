package com.zeroemotion.bfaa_kotlin_tmdb.data.source.repository

import androidx.lifecycle.LiveData
import com.zeroemotion.bfaa_kotlin_tmdb.data.model.Movie
import com.zeroemotion.bfaa_kotlin_tmdb.data.model.TvShow
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.remote.RemoteDataSource

class MovieRepositoryImpl(private val remoteDataSource: RemoteDataSource) : MovieRepository {
    override fun fetchMovieList(): LiveData<NetworkState<List<Movie>>> {
        return remoteDataSource.getMovieList()
    }

    override fun fetchTvList(): LiveData<NetworkState<List<TvShow>>> {
        return remoteDataSource.getTvList()
    }

    override fun fetchMovieDetail(id: Int): LiveData<NetworkState<Movie>> {
       return remoteDataSource.getMovieDetail(id)
    }

    override fun fetchTvDetail(id: Int): LiveData<NetworkState<TvShow>> {
        return remoteDataSource.getTvDetail(id)
    }

}