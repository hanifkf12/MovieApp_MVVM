package com.hanifkf12.moviecatalogsubmission3.repository

import android.util.Log
import com.hanifkf12.moviecatalogsubmission3.database.MovieDao
import com.hanifkf12.moviecatalogsubmission3.model.movie.Movie

class MovieDbRepository(private val movieDao: MovieDao){

    suspend fun getMoviesDb(onQuery : (List<Movie>)->Unit){
        val movies = movieDao.getMoviesDb()
        onQuery(movies)
    }

    suspend fun insert(movie: Movie, onSuccess : (Boolean)->Unit){
        movieDao.insert(movie).apply {
            Log.d("INSERT", "Success insert movie")
            onSuccess(true)
        }
    }

    suspend fun getMovieDb(id : Int, onQuery : (Movie?)->Unit){
        val movie = movieDao.getSingleMovieDb(id)
        onQuery(movie)
    }

    suspend fun deleteMovieDb(id: Int,onSuccess : (Boolean)->Unit ){
        movieDao.deleteSingleMovieDb(id).apply {
            Log.d("DELETE", "Success delete movie")
            onSuccess(false)
        }
    }
}