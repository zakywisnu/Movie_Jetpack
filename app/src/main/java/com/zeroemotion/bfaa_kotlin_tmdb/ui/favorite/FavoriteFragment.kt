package com.zeroemotion.bfaa_kotlin_tmdb.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.zeroemotion.bfaa_kotlin_tmdb.R
import com.zeroemotion.bfaa_kotlin_tmdb.databinding.FragmentFavoriteBinding
import com.zeroemotion.bfaa_kotlin_tmdb.ui.base.MainActivity

class FavoriteFragment : Fragment() {
    private lateinit var dataBinding: FragmentFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pagerAdapter = context?.let { FavoriteViewPager(it, childFragmentManager) }
        dataBinding.apply {
            viewPagerFavorite.adapter = pagerAdapter
            favoriteTab.setupWithViewPager(viewPagerFavorite)
        }

        context?.let {
            (it as MainActivity).supportActionBar?.elevation = 0f
        }
    }
}