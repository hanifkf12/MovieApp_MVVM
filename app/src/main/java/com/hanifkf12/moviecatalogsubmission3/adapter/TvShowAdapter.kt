package com.hanifkf12.moviecatalogsubmission3.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hanifkf12.moviecatalogsubmission3.R
import com.hanifkf12.moviecatalogsubmission3.model.tvshow.TvShow
import kotlinx.android.synthetic.main.item_movie.view.*

class TvShowAdapter(private val context : Context?, private val list: List<TvShow>, private val listener : (TvShow) -> Unit) : RecyclerView.Adapter<TvShowAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_tv,parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(list[position], listener)
    }


    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(tvShow: TvShow, listener: (TvShow) -> Unit){
            itemView.text_title.text = tvShow.name
            itemView.text_detail_year.text = tvShow.firstAirDate
            itemView.iv_poster.contentDescription = tvShow.name
            tvShow.posterPath?.let {
                Glide.with(itemView).load("https://image.tmdb.org/t/p/w500$it").into(itemView.iv_poster)
            }
            itemView.setOnClickListener {
                listener(tvShow)
            }
        }
    }
}