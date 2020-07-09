package com.hanifkf12.moviecatalogsubmission3.view.tvshow

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hanifkf12.moviecatalogsubmission3.R
import com.hanifkf12.moviecatalogsubmission3.model.tvshow.TvShow
import com.hanifkf12.moviecatalogsubmission3.repository.TvShowRepository

class TvShowViewModel : ViewModel() {
    private val repository = TvShowRepository()
    val tvShows: MutableLiveData<List<TvShow>?> = MutableLiveData()
    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val error: MutableLiveData<String> = MutableLiveData()

    fun getTvShow(context: Context?) {
        loading.value = true
        repository.getTvShow({
            Log.d("Tv Show", it.results.size.toString())
            tvShows.value = it.results
            loading.value = false
        }, {
            context?.let {
                error.value = it.getString(R.string.fail_load)
            }
            loading.value = false
        })
    }
}
