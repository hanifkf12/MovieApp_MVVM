package com.hanifkf12.moviecatalogsubmission3.view.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout

import com.hanifkf12.moviecatalogsubmission3.R
import com.hanifkf12.moviecatalogsubmission3.adapter.SectionsPagerAdapter
import com.hanifkf12.moviecatalogsubmission3.view.favorite.viewmodel.MovieDbViewModel
import kotlinx.android.synthetic.main.fragment_favorite.*

/**
 * A simple [Fragment] subclass.
 */
class FavoriteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sectionsPagerAdapter =
            SectionsPagerAdapter(
                context!!,
                childFragmentManager
            )
        vp_fav.adapter = sectionsPagerAdapter
        tab_fav.setupWithViewPager(vp_fav)
    }
}
