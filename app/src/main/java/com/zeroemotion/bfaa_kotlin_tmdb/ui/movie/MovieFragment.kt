package com.zeroemotion.bfaa_kotlin_tmdb.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.zeroemotion.bfaa_kotlin_tmdb.R
import com.zeroemotion.bfaa_kotlin_tmdb.databinding.FragmentMovieBinding
import kotlinx.android.synthetic.main.fragment_movie.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {

    private val viewModel: MovieViewModel by viewModel()
    private var movieAdapter =
        MovieAdapter(arrayListOf())
    private lateinit var dataBinding: FragmentMovieBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvMovie.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = movieAdapter
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.getMovie().observe(viewLifecycleOwner, Observer { movie ->
            movie?.let {
                rvMovie.visibility = View.VISIBLE
                movieAdapter.updateMovieList(it)
            }
        })
    }

}
