package com.hanifkf12.moviecatalogsubmission3.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.hanifkf12.moviecatalogsubmission3.R
import com.hanifkf12.moviecatalogsubmission3.view.favorite.FavoriteMovieFragment
import com.hanifkf12.moviecatalogsubmission3.view.favorite.FavoriteTvFragment

private val TAB_TITLES = arrayOf(
        R.string.title_movie,
        R.string.title_tv
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager)
    : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragments = listOf(FavoriteMovieFragment(), FavoriteTvFragment())
    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 2
    }
}