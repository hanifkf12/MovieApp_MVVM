package com.hanifkf12.moviecatalogsubmission3.network

import com.hanifkf12.moviecatalogsubmission3.model.movie.MovieResponse
import com.hanifkf12.moviecatalogsubmission3.model.tvshow.TvShowResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("movie/popular")
    fun getMovie(@Query("api_key") apiKey: String): Observable<MovieResponse>

    @GET("tv/popular")
    fun getTvShow(@Query("api_key") apiKey: String): Observable<TvShowResponse>

    @GET("search/movie")
    fun searchMovie(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): Observable<MovieResponse>

    @GET("search/tv")
    fun searchTvShow(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): Observable<TvShowResponse>

    @GET("discover/movie")
    fun getReleaseMovie(
        @Query("api_key") apiKey: String,
        @Query("primary_release_date.gte") gte: String,
        @Query("primary_release_date.lte") lte: String
    ): Observable<MovieResponse>
}