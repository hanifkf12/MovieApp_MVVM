package com.hanifkf12.moviecatalogsubmission3.view.favorite

import android.content.Intent
import android.icu.util.ValueIterator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hanifkf12.moviecatalogsubmission3.DetailActivity

import com.hanifkf12.moviecatalogsubmission3.R
import com.hanifkf12.moviecatalogsubmission3.adapter.TvShowAdapter
import com.hanifkf12.moviecatalogsubmission3.model.tvshow.TvShow
import com.hanifkf12.moviecatalogsubmission3.view.favorite.viewmodel.TvShowDbViewModel
import kotlinx.android.synthetic.main.fragment_favorite_tv.*

/**
 * A simple [Fragment] subclass.
 */
class FavoriteTvFragment : Fragment() {
    companion object {
        const val EXTRA_TV = "tvku"
        const val IDENTITY = "identity"
    }

    private lateinit var viewModel: TvShowDbViewModel
    private lateinit var adapter: TvShowAdapter
    private var listTv: MutableList<TvShow> = mutableListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_tv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(TvShowDbViewModel::class.java)
        adapter = TvShowAdapter(context, listTv) {
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(EXTRA_TV, it)
            intent.putExtra(IDENTITY, "TvShow")
            startActivity(intent)
        }
        rv_fav_tv.layoutManager = LinearLayoutManager(context)
        rv_fav_tv.adapter = adapter
        viewModel.getTvShowsDb()
        viewModel.loading.observe(viewLifecycleOwner, Observer {
            if (it) {
                loading_fav_tv.visibility = View.VISIBLE
            } else {
                loading_fav_tv.visibility = View.GONE
            }
        })
        viewModel.tvShowDb.observe(viewLifecycleOwner, Observer {
            it?.let {
                empty_list_tv.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
                listTv.clear()
                listTv.addAll(it)
                adapter.notifyDataSetChanged()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getTvShowsDb()

    }
}
