package com.zeroemotion.bfaa_kotlin_tmdb.ui.favorite.favtvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.zeroemotion.bfaa_kotlin_tmdb.R
import com.zeroemotion.bfaa_kotlin_tmdb.databinding.FragmentFavoriteTvShowBinding
import kotlinx.android.synthetic.main.fragment_favorite_tv_show.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteTvShowFragment : Fragment() {
    private val viewModel: FavoriteTvShowViewModel by viewModel()
    private val tvAdapter = FavoriteTvShowAdapter()
    private lateinit var dataBinding: FragmentFavoriteTvShowBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_favorite_tv_show, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvFavTvShow.apply {
            adapter = tvAdapter
            layoutManager = GridLayoutManager(context,2)
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.getFavoriteTvShow().observe(viewLifecycleOwner, Observer {
            tvAdapter.submitList(it)
            tvAdapter.notifyDataSetChanged()
        })
    }

}