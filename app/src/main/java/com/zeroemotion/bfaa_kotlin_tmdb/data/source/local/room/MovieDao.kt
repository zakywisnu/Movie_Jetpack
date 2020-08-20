package com.zeroemotion.bfaa_kotlin_tmdb.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.local.entity.MovieEntity
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.local.entity.TvShowEntity

@Dao
interface MovieDao{

    @Query("SELECT * FROM movieentity")
    fun getAllMovie(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tvshowentity")
    fun getAllTvShow(): DataSource.Factory<Int, TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(tvShow: List<TvShowEntity>)

    @Query("SELECT * FROM movieentity WHERE id = :id")
    fun getMovieById(id: Int): LiveData<MovieEntity>

    @Query("SELECT* FROM tvshowentity WHERE id = :id")
    fun getTvShowById(id: Int): LiveData<TvShowEntity>

    @Update
    fun updateMovie(movie:MovieEntity)

    @Update
    fun updateTvShow(tvs: TvShowEntity)

    @Query("SELECT * FROM movieentity WHERE favorite = 1")
    fun getFavoriteMovie(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tvshowentity WHERE favorite = 1")
    fun getFavoriteTvShow(): DataSource.Factory<Int, TvShowEntity>

}