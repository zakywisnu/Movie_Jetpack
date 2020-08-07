package com.zeroemotion.bfaa_kotlin_tmdb.di

import com.zeroemotion.bfaa_kotlin_tmdb.data.source.remote.RemoteDataSource
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.repository.MovieRepository
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.repository.MovieRepositoryImpl
import com.zeroemotion.bfaa_kotlin_tmdb.ui.detail.DetailViewModel
import com.zeroemotion.bfaa_kotlin_tmdb.ui.movie.MovieViewModel
import com.zeroemotion.bfaa_kotlin_tmdb.ui.tvshow.TvShowViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val module = module {
    viewModel { MovieViewModel(get()) }
    viewModel { TvShowViewModel(get()) }
    viewModel { (id: Int) -> DetailViewModel(get(), id) }

    factory<MovieRepository> { MovieRepositoryImpl(get()) }
    factory { RemoteDataSource() }
}