package com.zeroemotion.bfaa_kotlin_tmdb.di

import com.zeroemotion.bfaa_kotlin_tmdb.data.source.local.LocalDataSource
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.local.room.MovieDatabase
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.remote.RemoteDataSource
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.repository.MovieRepository
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.repository.MovieRepositoryImpl
import com.zeroemotion.bfaa_kotlin_tmdb.ui.detail.DetailViewModel
import com.zeroemotion.bfaa_kotlin_tmdb.ui.favorite.favmovie.FavoriteMovieViewModel
import com.zeroemotion.bfaa_kotlin_tmdb.ui.favorite.favtvshow.FavoriteTvShowViewModel
import com.zeroemotion.bfaa_kotlin_tmdb.ui.movie.MovieViewModel
import com.zeroemotion.bfaa_kotlin_tmdb.ui.tvshow.TvShowViewModel
import com.zeroemotion.bfaa_kotlin_tmdb.util.AppExecutors
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val module = module {
    viewModel { MovieViewModel(get()) }
    viewModel { TvShowViewModel(get()) }
    viewModel { (id: Int) -> DetailViewModel(get(), id) }
    viewModel { FavoriteMovieViewModel(get()) }
    viewModel { FavoriteTvShowViewModel(get()) }

    factory<MovieRepository> { MovieRepositoryImpl(get(), get(), get()) }
    factory { RemoteDataSource() }
    factory { LocalDataSource(get()) }
    factory { AppExecutors() }
    factory { MovieDatabase.getInstace(get()).movieDao() }
}