package com.zeroemotion.bfaa_kotlin_tmdb.ui.favorite

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.zeroemotion.bfaa_kotlin_tmdb.ui.favorite.favmovie.FavoriteMovieFragment
import com.zeroemotion.bfaa_kotlin_tmdb.ui.favorite.favtvshow.FavoriteTvShowFragment

class FavoriteViewPager (context: Context,fm: FragmentManager): FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
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

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Favorite Movie"
            else -> "Favorite Tv Show"
        }
    }

}