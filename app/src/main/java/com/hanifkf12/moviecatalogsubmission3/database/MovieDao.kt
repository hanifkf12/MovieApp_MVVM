package com.hanifkf12.moviecatalogsubmission3.database

import androidx.room.*
import com.hanifkf12.moviecatalogsubmission3.model.movie.Movie

@Dao
interface MovieDao {
    @Query("SELECT * from movie_table")
    suspend fun getMoviesDb() : List<Movie>

    @Query("SELECT * from movie_table WHERE id = :id")
    suspend fun getSingleMovieDb(id : Int) : Movie?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(movie: Movie)

    @Query("DELETE from movie_table WHERE id = :id")
    suspend fun deleteSingleMovieDb(id : Int)

}