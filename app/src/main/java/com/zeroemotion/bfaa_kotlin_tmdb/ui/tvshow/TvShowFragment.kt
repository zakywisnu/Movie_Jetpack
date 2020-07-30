package com.zeroemotion.bfaa_kotlin_tmdb.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.zeroemotion.bfaa_kotlin_tmdb.R
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.repository.NetworkState
import com.zeroemotion.bfaa_kotlin_tmdb.databinding.FragmentTvShowBinding
import kotlinx.android.synthetic.main.fragment_tv_show.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowFragment : Fragment() {

    private val viewModel: TvShowViewModel by viewModel()
    private var tvAdapter =
        TvShowAdapter(arrayListOf())
    private lateinit var dataBinding: FragmentTvShowBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_tv_show, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvTvShow.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = tvAdapter
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.getTvs().observe(viewLifecycleOwner, Observer { movie ->
            movie?.let {
                rvTvShow.visibility = View.VISIBLE
                tvAdapter.updateTvList(it.results)
            }
        })

        viewModel.networkState.observe(viewLifecycleOwner, Observer { state ->
            state?.let {
                tvLoading.visibility = if (it == NetworkState.LOADING) View.VISIBLE else View.GONE
                tvError.visibility = if (it == NetworkState.ERROR) View.VISIBLE else View.GONE
            }
        })
    }

}
