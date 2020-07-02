package com.zeroemotion.bfaa_kotlin_tmdb.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.zeroemotion.bfaa_kotlin_tmdb.R
import com.zeroemotion.bfaa_kotlin_tmdb.databinding.FragmentDetailMovieBinding
import com.zeroemotion.bfaa_kotlin_tmdb.viewmodel.DetailViewModel

/**
 * A simple [Fragment] subclass.
 */
class DetailMovieFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel
    private var movieId = 0
    private lateinit var dataBinding: FragmentDetailMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_detail_movie,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        arguments?.let {
            movieId = DetailMovieFragmentArgs.fromBundle(it).movieId
        }

        viewModel.fetchMovie(movieId)

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.movieLiveData.observe(viewLifecycleOwner, Observer { movie ->
            movie?.let {
                dataBinding.movie = movie
            }
        })
    }

}
