package com.zeroemotion.bfaa_kotlin_tmdb.viewmodel

import com.zeroemotion.bfaa_kotlin_tmdb.R
import com.zeroemotion.bfaa_kotlin_tmdb.model.DataDummy
import com.zeroemotion.bfaa_kotlin_tmdb.model.Movie
import com.zeroemotion.bfaa_kotlin_tmdb.model.TvShow
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel
    private var movie: Movie? = null
    private var tvShow: TvShow? = null

    @Before
    fun setUp() {
        viewModel = DetailViewModel()
        movie = Movie(
            1,
            "Alita : Battle Angel",
            "Robert Rodriguez",
            "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
            R.drawable.poster_alita,
            "2019",
            "67"
        )

        tvShow = TvShow(
            1,
            "Arrow",
            "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
            R.drawable.poster_arrow,
            "2012",
            "58"
        )
    }

    @Test
    fun detailMovie(){
        assertEquals(movie?.id, viewModel.getDetailMovie(1)?.id)
        assertEquals(movie?.title, viewModel.getDetailMovie(1)?.title)
        assertEquals(movie?.releaseDate, viewModel.getDetailMovie(1)?.releaseDate)
        assertEquals(movie?.voteAverage, viewModel.getDetailMovie(1)?.voteAverage)
        assertEquals(movie?.overview, viewModel.getDetailMovie(1)?.overview)
    }

    @Test
    fun detailTvShow(){
        assertEquals(tvShow?.id, viewModel.getDetailTv(1)?.id)
        assertEquals(tvShow?.name, viewModel.getDetailTv(1)?.name)
        assertEquals(tvShow?.firstAirDate, viewModel.getDetailTv(1)?.firstAirDate)
        assertEquals(tvShow?.voteAverage, viewModel.getDetailTv(1)?.voteAverage)
        assertEquals(tvShow?.overview, viewModel.getDetailTv(1)?.overview)
    }

}