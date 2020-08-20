package com.zeroemotion.bfaa_kotlin_tmdb.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zeroemotion.bfaa_kotlin_tmdb.data.model.Movie
import com.zeroemotion.bfaa_kotlin_tmdb.data.model.TvShow
import com.zeroemotion.bfaa_kotlin_tmdb.util.Api
import com.zeroemotion.bfaa_kotlin_tmdb.util.EspressoIdlingResource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit

class RemoteDataSource {
    private val retrofit: Retrofit = MovieService.instance
    private val movieService = retrofit.create(MovieApi::class.java)
    private val disposable: CompositeDisposable = CompositeDisposable()

    fun getMovieList(): LiveData<ApiResponse<List<Movie>>> {
        EspressoIdlingResource.increment()
        val resultMovie = MutableLiveData<ApiResponse<List<Movie>>>()
        disposable.add(
            movieService.getMovie(Api.PAGE, Api.LANGUAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    resultMovie.value = ApiResponse.success(it.results)
                    EspressoIdlingResource.decrement()
                }, {

                })
        )
        return resultMovie
    }

    fun getTvList(): LiveData<ApiResponse<List<TvShow>>> {
        EspressoIdlingResource.increment()
        val resultTv = MutableLiveData<ApiResponse<List<TvShow>>>()
        disposable.add(
            movieService.getTvShow(Api.PAGE, Api.LANGUAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    resultTv.value = ApiResponse.success(it.results)
                    EspressoIdlingResource.decrement()
                }, {

                })
        )
        return resultTv
    }


}