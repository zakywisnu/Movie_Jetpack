package com.zeroemotion.bfaa_kotlin_tmdb.data.source.repository

import androidx.lifecycle.LiveData
import com.zeroemotion.bfaa_kotlin_tmdb.data.model.Movie
import com.zeroemotion.bfaa_kotlin_tmdb.data.model.Response
import com.zeroemotion.bfaa_kotlin_tmdb.data.model.TvShow
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.remote.RemoteDataSource

class MovieRepository(private val remoteDataSource: RemoteDataSource) : MovieDataSource {

    override fun fetchMovieList(): LiveData<Response<Movie>> {
        remoteDataSource.getMovieList()
        return remoteDataSource.movieResponse
    }

    override fun fetchTvList(): LiveData<Response<TvShow>> {
        remoteDataSource.getTvList()
        return remoteDataSource.tvResponse
    }

    override fun fetchMovieDetail(id: Int): LiveData<Movie> {
        remoteDataSource.getMovieDetail(id)
        return remoteDataSource.movieDetail
    }

    override fun fetchTvDetail(id: Int): LiveData<TvShow> {
        remoteDataSource.getTvDetail(id)
        return remoteDataSource.tvDetail
    }

    override fun getNetworkState(): LiveData<NetworkState> {
        return remoteDataSource.networkState
    }


}