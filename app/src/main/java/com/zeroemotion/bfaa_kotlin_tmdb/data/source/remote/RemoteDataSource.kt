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

    private val _networkState: MutableLiveData<NetworkState> = MutableLiveData()
    val networkState: LiveData<NetworkState>
        get() = _networkState

    private val _movieResponse: MutableLiveData<Response<Movie>> = MutableLiveData()
    val movieResponse: LiveData<Response<Movie>>
        get() = _movieResponse

    private val _movieDetail: MutableLiveData<Movie> = MutableLiveData()
    val movieDetail: LiveData<Movie>
        get() = _movieDetail

    private val _tvResponse: MutableLiveData<Response<TvShow>> = MutableLiveData()
    val tvResponse: LiveData<Response<TvShow>>
        get() = _tvResponse

    private val _tvDetail: MutableLiveData<TvShow> = MutableLiveData()
    val tvDetail: LiveData<TvShow>
        get() = _tvDetail

    fun getMovieList() {
        EspressoIdlingResource.increment()
        _networkState.postValue(NetworkState.LOADING)
        disposable.add(
            movieService.getMovie()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _movieResponse.postValue(it)
                    _networkState.postValue(NetworkState.LOADED)
                    EspressoIdlingResource.decrement()
                }, {
                    _networkState.postValue(NetworkState.ERROR)
                    EspressoIdlingResource.decrement()
                })
        )
    }

    fun getMovieDetail(id: Int) {
        EspressoIdlingResource.increment()
        _networkState.postValue(NetworkState.LOADING)
        disposable.add(
            movieService.getMovieDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _movieDetail.postValue(it)
                    _networkState.postValue(NetworkState.LOADED)
                    EspressoIdlingResource.decrement()
                }, {
                    _networkState.postValue(NetworkState.ERROR)
                    EspressoIdlingResource.decrement()
                })
        )
    }

    fun getTvList() {
        EspressoIdlingResource.increment()
        _networkState.postValue(NetworkState.LOADING)
        disposable.add(
            movieService.getTvShow()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _tvResponse.postValue(it)
                    _networkState.postValue(NetworkState.LOADED)
                    EspressoIdlingResource.decrement()
                }, {
                    _networkState.postValue(NetworkState.ERROR)
                    EspressoIdlingResource.decrement()
                })
        )
    }

    fun getTvDetail(id: Int) {
        EspressoIdlingResource.increment()
        _networkState.postValue(NetworkState.LOADING)
        disposable.add(
            movieService.getTvDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _tvDetail.postValue(it)
                    _networkState.postValue(NetworkState.LOADED)
                    EspressoIdlingResource.decrement()
                }, {
                    _networkState.postValue(NetworkState.ERROR)
                    EspressoIdlingResource.decrement()
                })
        )
    }
}