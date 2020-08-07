package com.zeroemotion.bfaa_kotlin_tmdb.data.source.remote

import com.zeroemotion.bfaa_kotlin_tmdb.data.model.Movie
import com.zeroemotion.bfaa_kotlin_tmdb.data.model.TvShow
import com.zeroemotion.bfaa_kotlin_tmdb.util.EspressoIdlingResource
import retrofit2.Retrofit
import retrofit2.await

class RemoteDataSource {
    private val retrofit: Retrofit = MovieService.instance
    private val movieService = retrofit.create(MovieApi::class.java)

    suspend fun getMovieList(callback: LoadPopularMovies) {
        EspressoIdlingResource.increment()
        movieService.getMovie().await().results.let {
            callback.onAllMoviesReceived(it)
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getMovieDetail(id: Int, callback: LoadMovieDetailCallback) {
        EspressoIdlingResource.increment()
        movieService.getMovieDetail(id)
            .await().let {
                callback.onMovieDetailReceived(it)
                EspressoIdlingResource.decrement()
            }
    }

    suspend fun getTvList(callback: LoadPopularTvShow) {
        EspressoIdlingResource.increment()
        movieService.getTvShow()
            .await().results.let { listTv ->
                callback.onAllTvShowsReceived(listTv)
                EspressoIdlingResource.decrement()
            }
    }

    suspend fun getTvDetail(id: Int, callback: LoadTvShowDetailCallback) {
        EspressoIdlingResource.increment()
        movieService.getTvDetail(id)
            .await().let {
                callback.onTvShowDetailReceived(it)
                EspressoIdlingResource.decrement()
            }
    }

    interface LoadPopularMovies {
        fun onAllMoviesReceived(movieResponse: List<Movie>)
    }

    interface LoadMovieDetailCallback {
        fun onMovieDetailReceived(movieResponse: Movie)
    }

    interface LoadPopularTvShow {
        fun onAllTvShowsReceived(tvShowResponse: List<TvShow>)
    }

    interface LoadTvShowDetailCallback {
        fun onTvShowDetailReceived(tvShowResponse: TvShow)
    }
}