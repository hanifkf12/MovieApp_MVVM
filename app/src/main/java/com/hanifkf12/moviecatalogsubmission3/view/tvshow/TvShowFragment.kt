package com.hanifkf12.moviecatalogsubmission3.view.tvshow

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hanifkf12.moviecatalogsubmission3.DetailActivity

import com.hanifkf12.moviecatalogsubmission3.R
import com.hanifkf12.moviecatalogsubmission3.adapter.MovieAdapter
import com.hanifkf12.moviecatalogsubmission3.adapter.TvShowAdapter
import com.hanifkf12.moviecatalogsubmission3.model.tvshow.TvShow
import kotlinx.android.synthetic.main.tv_show_fragment.*

class TvShowFragment : Fragment() {

    companion object {
        const val EXTRA_TV = "tvku"
        const val IDENTITY = "identity"
    }

    private lateinit var viewModel: TvShowViewModel
    private lateinit var adapter: TvShowAdapter
    private var listTv: MutableList<TvShow> = mutableListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.tv_show_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(TvShowViewModel::class.java)
        adapter = TvShowAdapter(context, listTv) {
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(EXTRA_TV, it)
            intent.putExtra(IDENTITY, "TvShow")
            startActivity(intent)
        }
        rv_tv.layoutManager = LinearLayoutManager(context)
        rv_tv.adapter = adapter
        viewModel.getTvShow(context)
        viewModel.loading.observe(viewLifecycleOwner, Observer {
            if (it) {
                loading_tv.visibility = View.VISIBLE
            } else {
                loading_tv.visibility = View.GONE
            }
        })
        viewModel.error.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })
        viewModel.tvShows.observe(viewLifecycleOwner, Observer {
            it?.let {
                listTv.clear()
                listTv.addAll(it)
                adapter.notifyDataSetChanged()
            }
        })
    }
}
