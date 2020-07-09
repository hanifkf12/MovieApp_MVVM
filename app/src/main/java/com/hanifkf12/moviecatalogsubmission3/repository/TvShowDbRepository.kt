package com.hanifkf12.moviecatalogsubmission3.repository

import android.util.Log
import com.hanifkf12.moviecatalogsubmission3.database.TvShowDao
import com.hanifkf12.moviecatalogsubmission3.model.tvshow.TvShow

class TvShowDbRepository(val tvShowDao: TvShowDao){
    suspend fun getTvShowsDb(onQuery : (List<TvShow>)->Unit){
        val movies = tvShowDao.getTvDb()
        onQuery(movies)
    }

    suspend fun insert(tvShow: TvShow, onSuccess : (Boolean)->Unit){
        tvShowDao.insert(tvShow).apply {
            Log.d("INSERT", "Success insert tv")
            onSuccess(true)
        }
    }

    suspend fun getTvDb(id : Int, onQuery : (TvShow?)->Unit){
        val movie = tvShowDao.getSingleTvDb(id)
        onQuery(movie)
    }

    suspend fun deleteTvDb(id: Int,onSuccess : (Boolean)->Unit ){
        tvShowDao.deleteSingleTvDb(id).apply {
            Log.d("DELETE", "Success delete movie")
            onSuccess(false)
        }
    }
}