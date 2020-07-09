package com.hanifkf12.moviecatalogsubmission3.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hanifkf12.moviecatalogsubmission3.model.tvshow.TvShow

@Dao
interface TvShowDao {
    @Query("SELECT * from tv_table")
    suspend fun getTvDb() : List<TvShow>

    @Query("SELECT * from tv_table WHERE id = :id")
    suspend fun getSingleTvDb(id : Int) : TvShow?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(tvShow: TvShow)

    @Query("DELETE from tv_table WHERE id = :id")
    suspend fun deleteSingleTvDb(id : Int)
}