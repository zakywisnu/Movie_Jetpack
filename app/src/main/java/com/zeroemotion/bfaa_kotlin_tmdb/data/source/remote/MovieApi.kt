package com.zeroemotion.bfaa_kotlin_tmdb.data.source.remote

import com.zeroemotion.bfaa_kotlin_tmdb.BuildConfig.API_KEY
import com.zeroemotion.bfaa_kotlin_tmdb.data.model.Movie
import com.zeroemotion.bfaa_kotlin_tmdb.data.model.Response
import com.zeroemotion.bfaa_kotlin_tmdb.data.model.TvShow
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/popular?api_key=$API_KEY")
    fun getMovie(
        @Query("page") page: Int,
        @Query("language") language: String
    ): Observable<Response<Movie>>

    @GET("tv/popular?api_key=$API_KEY")
    fun getTvShow(
        @Query("page") page: Int,
        @Query("language") language: String
    ): Observable<Response<TvShow>>

    @GET("movie/{movie_id}?api_key=$API_KEY")
    fun getMovieDetail(@Path("movie_id") movie_id: Int): Observable<Movie>

    @GET("tv/{tv_id}?api_key=$API_KEY")
    fun getTvDetail(@Path("tv_id") tv_id: Int): Observable<TvShow>
}