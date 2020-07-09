package com.hanifkf12.moviecatalogsubmission3.adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hanifkf12.moviecatalogsubmission3.R
import com.hanifkf12.moviecatalogsubmission3.model.movie.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter (private val context: Context?, private val list: List<Movie>, private val listener : (Movie)->Unit) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_movie,parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(list[position], listener)
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(movie: Movie, listener: (Movie) -> Unit){
            itemView.text_title.text = movie.title
            itemView.iv_poster.contentDescription = movie.title
            itemView.text_detail_year.text = movie.releaseDate
            movie.posterPath?.let {
                Glide.with(itemView).load("https://image.tmdb.org/t/p/w500$it").into(itemView.iv_poster)
            }
            itemView.setOnClickListener {
                listener(movie)
            }
        }
    }

}