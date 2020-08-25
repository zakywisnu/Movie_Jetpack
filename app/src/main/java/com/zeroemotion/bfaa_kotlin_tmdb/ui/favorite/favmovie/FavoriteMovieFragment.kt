package com.zeroemotion.bfaa_kotlin_tmdb.ui.favorite.favmovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.zeroemotion.bfaa_kotlin_tmdb.R
import com.zeroemotion.bfaa_kotlin_tmdb.databinding.FragmentFavoriteMovieBinding
import com.zeroemotion.bfaa_kotlin_tmdb.ui.movie.MovieAdapter
import kotlinx.android.synthetic.main.fragment_favorite_movie.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteMovieFragment : Fragment() {
    private val viewModel: FavoriteMovieViewModel by viewModel()
    private lateinit var dataBinding: FragmentFavoriteMovieBinding
    private val movieAdapter = FavoriteMovieAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_favorite_movie, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        rvFavMovie.apply {
            adapter = movieAdapter
            layoutManager = GridLayoutManager(context,2)
        }
    }

    private fun observeViewModel() {
        viewModel.getFavoriteMovie().observe(viewLifecycleOwner, Observer {movies ->
            movieAdapter.submitList(movies)
            movieAdapter.notifyDataSetChanged()
        })
    }
}