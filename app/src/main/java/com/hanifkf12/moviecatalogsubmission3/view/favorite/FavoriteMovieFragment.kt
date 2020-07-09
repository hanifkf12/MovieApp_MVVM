package com.hanifkf12.moviecatalogsubmission3.view.favorite

import android.content.Intent
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
import com.hanifkf12.moviecatalogsubmission3.adapter.MovieAdapter
import com.hanifkf12.moviecatalogsubmission3.model.movie.Movie
import com.hanifkf12.moviecatalogsubmission3.view.favorite.viewmodel.MovieDbViewModel
import kotlinx.android.synthetic.main.fragment_favorite_movie.*

/**
 * A simple [Fragment] subclass.
 */
class FavoriteMovieFragment : Fragment() {
    companion object {
        const val EXTRA_MOVIE = "movieku"
        const val IDENTITY = "identity"
    }

    private var listMovie: MutableList<Movie> = mutableListOf()
    private lateinit var viewModel: MovieDbViewModel
    private lateinit var adapter: MovieAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MovieDbViewModel::class.java)
        adapter = MovieAdapter(context, listMovie) {
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(EXTRA_MOVIE, it)
            intent.putExtra(IDENTITY, "Movie")
            startActivity(intent)
        }
        rv_fav_movies.layoutManager = LinearLayoutManager(context)
        rv_fav_movies.adapter = adapter
        viewModel.getMoviesDb()
        viewModel.loading.observe(viewLifecycleOwner, Observer {
            if (it) {
                loading_fav_movie.visibility = View.VISIBLE
            } else {
                loading_fav_movie.visibility = View.GONE
            }
        })
        viewModel.moviesDb.observe(viewLifecycleOwner, Observer {

            it?.let {
                empty_list.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
                listMovie.clear()
                listMovie.addAll(it)
                adapter.notifyDataSetChanged()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getMoviesDb()
    }
}
