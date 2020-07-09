package com.hanifkf12.moviecatalogsubmission3.view.movie

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.hanifkf12.moviecatalogsubmission3.model.movie.Movie
import com.hanifkf12.moviecatalogsubmission3.repository.MovieRepository
import kotlinx.android.synthetic.main.movie_fragment.*

class MovieFragment : Fragment() {

    companion object {
        const val EXTRA_MOVIE = "movieku"
        const val IDENTITY = "identity"
    }
    private var listMovie: MutableList<Movie> = mutableListOf()
    private lateinit var viewModel: MovieViewModel
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        adapter = MovieAdapter(context, listMovie) {
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(EXTRA_MOVIE, it)
            intent.putExtra(IDENTITY, "Movie")
            startActivity(intent)
        }
        rv_movie.layoutManager = LinearLayoutManager(context)
        rv_movie.adapter = adapter
        viewModel.getMovie(context)
        viewModel.loading.observe(viewLifecycleOwner, Observer {
            if(it){
                loading_movie.visibility = View.VISIBLE
            }else{
                loading_movie.visibility = View.GONE
            }
        })
        viewModel.error.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context,it,Toast.LENGTH_SHORT).show()
        })
        viewModel.movies.observe(viewLifecycleOwner, Observer {
            it?.let {
                listMovie.clear()
                listMovie.addAll(it)
                adapter.notifyDataSetChanged()
            }
        })
    }
}
