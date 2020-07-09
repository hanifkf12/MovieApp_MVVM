package com.hanifkf12.moviecatalogsubmission3.repository

import com.hanifkf12.moviecatalogsubmission3.BuildConfig
import com.hanifkf12.moviecatalogsubmission3.model.movie.MovieResponse
import com.hanifkf12.moviecatalogsubmission3.network.ApiResource
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MovieRepository {
    companion object {
        const val apiKey = BuildConfig.TMDB_API_KEY
    }
    fun getMovie(onResult: (MovieResponse) -> Unit, onError: (Throwable) -> Unit) {
        ApiResource.create().getMovie(apiKey).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<MovieResponse> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: MovieResponse) {
                    onResult(t)
                }

                override fun onError(e: Throwable) {
                    onError(e)
                }

            }
            )
    }


}