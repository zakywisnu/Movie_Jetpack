package com.zeroemotion.bfaa_kotlin_tmdb.viewmodel.data

import androidx.lifecycle.LiveData
import com.zeroemotion.bfaa_kotlin_tmdb.data.model.Movie
import com.zeroemotion.bfaa_kotlin_tmdb.data.model.Response
import com.zeroemotion.bfaa_kotlin_tmdb.data.model.TvShow
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.remote.RemoteDataSource
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.repository.MovieRepository
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.repository.NetworkState

class FakeMovieRepository(private val remoteDataSource: RemoteDataSource): MovieRepository {
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