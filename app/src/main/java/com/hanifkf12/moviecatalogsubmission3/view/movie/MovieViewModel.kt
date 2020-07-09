package com.hanifkf12.moviecatalogsubmission3.view.movie

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hanifkf12.moviecatalogsubmission3.R
import com.hanifkf12.moviecatalogsubmission3.model.movie.Movie
import com.hanifkf12.moviecatalogsubmission3.repository.MovieRepository

class MovieViewModel : ViewModel() {

    private val repository = MovieRepository()
    val movies: MutableLiveData<List<Movie>?> = MutableLiveData()
    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val error: MutableLiveData<String> = MutableLiveData()

    fun getMovie(context: Context?) {
        loading.value = true
        repository.getMovie({
            Log.d("Movie", it.results.size.toString())
            movies.value = it.results
            loading.value = false
        }, {
            context?.let {
                error.value = it.getString(R.string.fail_load)
            }
            loading.value = false
        })
    }

}
