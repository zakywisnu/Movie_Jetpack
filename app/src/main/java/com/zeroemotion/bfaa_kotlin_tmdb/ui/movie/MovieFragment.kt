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
import com.zeroemotion.bfaa_kotlin_tmdb.vo.Status
import kotlinx.android.synthetic.main.fragment_movie.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {

    private val viewModel: MovieViewModel by viewModel()
    private val movieAdapter = MovieAdapter()
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
        viewModel.getMovie().observe(viewLifecycleOwner, Observer { movies ->
            if (movies != null) {
                when (movies.status) {
                    Status.LOADING -> movieLoading.visibility = View.VISIBLE
                    Status.ERROR -> {
                        movieError.visibility = View.VISIBLE
                        movieLoading.visibility = View.GONE
                    }
                    Status.SUCCESS -> {
                        movieLoading.visibility = View.GONE
                        movieAdapter.submitList(movies.data)
                        movieAdapter.notifyDataSetChanged()
                    }
                }
            }
        })
    }

}
