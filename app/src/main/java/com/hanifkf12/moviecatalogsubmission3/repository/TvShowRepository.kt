package com.hanifkf12.moviecatalogsubmission3.repository

import com.hanifkf12.moviecatalogsubmission3.BuildConfig
import com.hanifkf12.moviecatalogsubmission3.model.tvshow.TvShowResponse
import com.hanifkf12.moviecatalogsubmission3.network.ApiResource
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class TvShowRepository{
    companion object {
        const val apiKey = BuildConfig.TMDB_API_KEY
    }
    fun getTvShow(onResult: (TvShowResponse) -> Unit, onError: (Throwable) -> Unit) {
        ApiResource.create().getTvShow(apiKey).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<TvShowResponse> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: TvShowResponse) {
                    onResult(t)
                }

                override fun onError(e: Throwable) {
                    onError(e)
                }

            }
            )
    }
}