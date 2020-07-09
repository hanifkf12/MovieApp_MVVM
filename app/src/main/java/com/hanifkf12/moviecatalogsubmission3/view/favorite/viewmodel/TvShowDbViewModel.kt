package com.hanifkf12.moviecatalogsubmission3.view.favorite.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hanifkf12.moviecatalogsubmission3.database.MyDatabase
import com.hanifkf12.moviecatalogsubmission3.model.tvshow.TvShow
import com.hanifkf12.moviecatalogsubmission3.repository.TvShowDbRepository
import kotlinx.coroutines.launch

class TvShowDbViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TvShowDbRepository
    var status: MutableLiveData<Boolean> = MutableLiveData()
    var tvShowDb: MutableLiveData<List<TvShow>> = MutableLiveData()
    var tvShow: MutableLiveData<TvShow?> = MutableLiveData()
    var loading: MutableLiveData<Boolean> = MutableLiveData()

    init {
        val tvShowDao = MyDatabase.getDatabase(application).tvShowDao()
        repository = TvShowDbRepository(tvShowDao)
    }

    fun insert(tvShow: TvShow) = viewModelScope.launch {
        repository.insert(tvShow) {
            status.value = it
        }
    }

    fun getTvShowsDb() = viewModelScope.launch {
        loading.value = true
        repository.getTvShowsDb {
            tvShowDb.value = it
            loading.value = false
        }
    }

    fun getTvShowDb(id: Int) = viewModelScope.launch {
        repository.getTvDb(id) {
            tvShow.value = it
        }
    }

    fun deleteTvShowDb(id: Int) = viewModelScope.launch {
        repository.deleteTvDb(id) {
            status.value = it
        }
    }
}