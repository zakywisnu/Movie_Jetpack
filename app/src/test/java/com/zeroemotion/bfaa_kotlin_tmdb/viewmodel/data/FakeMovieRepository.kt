package com.zeroemotion.bfaa_kotlin_tmdb.viewmodel.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zeroemotion.bfaa_kotlin_tmdb.data.model.Movie
import com.zeroemotion.bfaa_kotlin_tmdb.data.model.TvShow
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.remote.RemoteDataSource
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.repository.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FakeMovieRepository(private val remoteDataSource: RemoteDataSource) : MovieRepository {

    override fun fetchMovieList(): LiveData<List<Movie>> {
        val movieResult = MutableLiveData<List<Movie>>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getMovieList(object : RemoteDataSource.LoadPopularMovies {
                override fun onAllMoviesReceived(movieResponse: List<Movie>) {
                    val movieList = ArrayList<Movie>()
                    for (response in movieResponse) {
                        val movie = Movie(
                            response.id,
                            response.title,
                            response.overview,
                            response.posterPath,
                            response.releaseDate,
                            response.voteAverage
                        )
                        movieList.add(movie)
                    }
                    movieResult.postValue(movieList)
                }
            })
        }
        return movieResult
    }

    override fun fetchTvList(): LiveData<List<TvShow>> {
        val tvResult = MutableLiveData<List<TvShow>>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getTvList(object : RemoteDataSource.LoadPopularTvShow {
                override fun onAllTvShowsReceived(tvShowResponse: List<TvShow>) {
                    val tvList = ArrayList<TvShow>()
                    for (response in tvShowResponse) {
                        val tvs = TvShow(
                            response.id,
                            response.name,
                            response.overview,
                            response.posterPath,
                            response.firstAirDate,
                            response.voteAverage
                        )
                        tvList.add(tvs)
                    }
                    tvResult.postValue(tvList)
                }

            })
        }
        return tvResult
    }

    override fun fetchMovieDetail(id: Int): LiveData<Movie> {
        val movieResult = MutableLiveData<Movie>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getMovieDetail(id, object : RemoteDataSource.LoadMovieDetailCallback {
                override fun onMovieDetailReceived(movieResponse: Movie) {
                    val movies = Movie(
                        movieResponse.id,
                        movieResponse.title,
                        movieResponse.overview,
                        movieResponse.posterPath,
                        movieResponse.releaseDate,
                        movieResponse.voteAverage
                    )
                    movieResult.postValue(movies)
                }
            })
        }
        return movieResult
    }

    override fun fetchTvDetail(id: Int): LiveData<TvShow> {
        val tvResult = MutableLiveData<TvShow>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getTvDetail(id, object : RemoteDataSource.LoadTvShowDetailCallback {
                override fun onTvShowDetailReceived(tvShowResponse: TvShow) {
                    val tvs = TvShow(
                        tvShowResponse.id,
                        tvShowResponse.name,
                        tvShowResponse.overview,
                        tvShowResponse.posterPath,
                        tvShowResponse.firstAirDate,
                        tvShowResponse.voteAverage
                    )
                    tvResult.postValue(tvs)
                }

            })
        }
        return tvResult
    }


}