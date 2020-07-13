package com.zeroemotion.bfaa_kotlin_tmdb.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager

import com.zeroemotion.bfaa_kotlin_tmdb.R
import com.zeroemotion.bfaa_kotlin_tmdb.adapter.MovieAdapter
import com.zeroemotion.bfaa_kotlin_tmdb.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie.*

/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : Fragment() {

    private lateinit var viewModel: MovieViewModel
    private var movieAdapter = MovieAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)

        val movies = viewModel.getMovies()
        movieAdapter.updateMovieList(movies)
        rvMovie.apply {
            layoutManager = GridLayoutManager(context,2)
            adapter = movieAdapter
        }

    }



}
