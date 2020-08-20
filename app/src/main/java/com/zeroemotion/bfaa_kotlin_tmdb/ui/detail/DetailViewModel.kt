package com.zeroemotion.bfaa_kotlin_tmdb.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.local.entity.MovieEntity
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.local.entity.TvShowEntity
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.repository.MovieRepository

class DetailViewModel(private val movieRepository: MovieRepository, private val id: Int) :
    ViewModel() {

    private fun getMovieRepo() = movieRepository.getMovieDetail(id)
    var getMovieDetail: LiveData<MovieEntity> = getMovieRepo()

    private fun getTvRepo() = movieRepository.getTvDetail(id)
    var getTvDetail: LiveData<TvShowEntity> = getTvRepo()

    fun setFavoriteMovie(){
        val movie = getMovieDetail.value
        if (movie != null){
            val newState = !movie.favorite
            movieRepository.setFavoriteMovie(movie,newState)
        }
    }
    fun setFavoriteTvShow(){
        val tvs = getTvDetail.value
        if (tvs != null){
            val newState = !tvs.favorite
            movieRepository.setFavoriteTvShow(tvs,newState)
        }
    }

}