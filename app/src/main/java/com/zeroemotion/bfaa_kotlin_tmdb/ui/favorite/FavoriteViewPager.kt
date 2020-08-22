package com.zeroemotion.bfaa_kotlin_tmdb.ui.favorite

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.zeroemotion.bfaa_kotlin_tmdb.R
import com.zeroemotion.bfaa_kotlin_tmdb.ui.favorite.favmovie.FavoriteMovieFragment
import com.zeroemotion.bfaa_kotlin_tmdb.ui.favorite.favtvshow.FavoriteTvShowFragment

class FavoriteViewPager (private val context: Context,fm: FragmentManager): FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    companion object {
        @StringRes
        private val TITLE_TAB = intArrayOf(R.string.favorite_movie, R.string.favorite_tv)
    }


    private val pages = listOf(
        FavoriteMovieFragment(),
        FavoriteTvShowFragment()
    )
    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? = context?.resources.getString(
        TITLE_TAB[position]
    )

}