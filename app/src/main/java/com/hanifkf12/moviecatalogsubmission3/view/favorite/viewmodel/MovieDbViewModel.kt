package com.hanifkf12.moviecatalogsubmission3.view.favorite.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hanifkf12.moviecatalogsubmission3.database.MyDatabase
import com.hanifkf12.moviecatalogsubmission3.model.movie.Movie
import com.hanifkf12.moviecatalogsubmission3.repository.MovieDbRepository
import kotlinx.coroutines.launch

class MovieDbViewModel(application: Application) : AndroidViewModel(application){

    private val repository: MovieDbRepository by lazy {
        val movieDao = MyDatabase.getDatabase(application).movieDao()
        MovieDbRepository(movieDao)
    }
    var status : MutableLiveData<Boolean> = MutableLiveData()
    var moviesDb : MutableLiveData<List<Movie>> = MutableLiveData()
    var movie : MutableLiveData<Movie?> = MutableLiveData()
    var loading : MutableLiveData<Boolean> = MutableLiveData()
    init {

    }
    fun insert(movie: Movie) = viewModelScope.launch{
        repository.insert(movie){
            status.value = it
        }
    }

    fun getMoviesDb() = viewModelScope.launch{
        loading.value = true
        repository.getMoviesDb {
            moviesDb.value = it
            loading.value = false
        }
    }

    fun getMovieDb(id : Int) = viewModelScope.launch {
        repository.getMovieDb(id){
            movie.value = it
        }
    }

    fun deleteMovieDb(id:Int) = viewModelScope.launch {
        repository.deleteMovieDb(id){
            status.value = it
        }
    }
}