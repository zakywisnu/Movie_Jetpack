package com.zeroemotion.bfaa_kotlin_tmdb.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zeroemotion.bfaa_kotlin_tmdb.data.model.Movie
import com.zeroemotion.bfaa_kotlin_tmdb.data.model.Response
import com.zeroemotion.bfaa_kotlin_tmdb.data.model.TvShow
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.repository.NetworkState
import com.zeroemotion.bfaa_kotlin_tmdb.util.EspressoIdlingResource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit

class RemoteDataSource {
    private val retrofit: Retrofit = MovieService.instance
    private val movieService = retrofit.create(MovieApi::class.java)
    private val disposable = CompositeDisposable()

    fun getMovieList(): LiveData<NetworkState<List<Movie>>> {
        EspressoIdlingResource.increment()
        val movieResult = MutableLiveData<NetworkState<List<Movie>>>()
        disposable.add(
            movieService.getMovie()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ movie ->
                    movie?.let {
                        movieResult.value = NetworkState.success(it.results)
                        EspressoIdlingResource.decrement()
                    }
                }, {
                    it.message?.let { its ->
                        val list = ArrayList<Movie>()
                        movieResult.value = NetworkState.error(its, list)
                        EspressoIdlingResource.decrement()
                    }
                })
        )
        return movieResult
    }

    fun getMovieDetail(id: Int): LiveData<NetworkState<Movie>>{
        EspressoIdlingResource.increment()
        val movies = MutableLiveData<NetworkState<Movie>>()
        disposable.add(
            movieService.getMovieDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({movie ->
                    movie?.let {
                        movies.value = NetworkState.success(it)
                        EspressoIdlingResource.decrement()
                    }
                }, {
                    it.message?.let { its ->
                        val movies2 = Movie(1,"","","","","")
                        movies.value = NetworkState.error(its, movies2)
                        EspressoIdlingResource.decrement()
                    }
                })
        )
        return movies
    }

    fun getTvList(): LiveData<NetworkState<List<TvShow>>> {
        EspressoIdlingResource.increment()
        val tvResult = MutableLiveData<NetworkState<List<TvShow>>>()
        disposable.add(
            movieService.getTvShow()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ tvs ->
                    tvs?.let {
                        tvResult.value = NetworkState.success(it.results)
                        EspressoIdlingResource.decrement()
                    }
                }, {
                    it.message?.let { it1 ->
                        val list = ArrayList<TvShow>()
                        tvResult.value = NetworkState.error(it1, list)
                        EspressoIdlingResource.decrement()
                    }
                })
        )
        return tvResult
    }

    fun getTvDetail(id: Int) : LiveData<NetworkState<TvShow>> {
        EspressoIdlingResource.increment()
        val tvShow = MutableLiveData<NetworkState<TvShow>>()
        disposable.add(
            movieService.getTvDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({tvs ->
                    tvs.let {
                        tvShow.value = NetworkState.success(it)
                        EspressoIdlingResource.decrement()
                    }

                }, {
                    it.message?.let { its ->
                    val tvshows = TvShow(1,"","","","","")
                   tvShow.value = NetworkState.error(its, tvshows)
                    EspressoIdlingResource.decrement()
                }
                })
        )
        return tvShow
    }
}