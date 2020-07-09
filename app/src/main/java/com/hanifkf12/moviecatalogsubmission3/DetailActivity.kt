package com.hanifkf12.moviecatalogsubmission3

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.hanifkf12.moviecatalogsubmission3.model.movie.Movie
import com.hanifkf12.moviecatalogsubmission3.model.tvshow.TvShow
import com.hanifkf12.moviecatalogsubmission3.view.favorite.viewmodel.MovieDbViewModel
import com.hanifkf12.moviecatalogsubmission3.view.favorite.viewmodel.TvShowDbViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_MOVIE = "movieku"
        const val EXTRA_TV = "tvku"
        const val IDENTITY = "identity"
    }

    var isFavorite = false
    lateinit var viewModel: MovieDbViewModel
    lateinit var viewModelTvDb : TvShowDbViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = R.drawable.bg_transparant
        }
        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.container)
        ) { _, insets ->
            Log.d("INSETS", insets.systemWindowInsetTop.toString())
            iv_back.setMargin(insets.systemWindowInsetTop + 40, insets.systemWindowInsetLeft + 40)
            insets.consumeSystemWindowInsets()
        }

        viewModel = ViewModelProvider(this).get(MovieDbViewModel::class.java)
        viewModelTvDb = ViewModelProvider(this).get(TvShowDbViewModel::class.java)


        viewModel.movie.observe(this, Observer {
            it?.let {
                isFavorite = !isFavorite
                favoriteState()
            }
        })

        viewModelTvDb.tvShow.observe(this, Observer {
            it?.let {
                isFavorite = !isFavorite
                favoriteState()
            }
        })
        viewModel.status.observe(this, Observer {
            if(it)
                Toast.makeText(this, getString(R.string.add_favorite), Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this, getString(R.string.remove_favorite), Toast.LENGTH_SHORT).show()
        })
        viewModelTvDb.status.observe(this, Observer {
            if(it)
                Toast.makeText(this, getString(R.string.add_favorite), Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this, getString(R.string.remove_favorite), Toast.LENGTH_SHORT).show()

        })

        loading_detail.visibility = View.VISIBLE

        val intent = intent
        when (intent.getStringExtra(IDENTITY)) {
            "Movie" -> {
                val movie = intent.getParcelableExtra<Movie>(EXTRA_MOVIE)
                movie?.let {
                    text_detail_title.text = it.title
                    text_detail_year.text = it.releaseDate
                    text_overview.text = it.overview
                    text_rating.text = it.voteAverage.toString()
                    it.posterPath.let { it2 ->
                        Glide.with(this).load("https://image.tmdb.org/t/p/w500$it2")
                            .into(iv_detail_poster)
                    }
                    viewModel.getMovieDb(movie.id)
                    add_fav.setOnClickListener {
                        if (isFavorite) viewModel.deleteMovieDb(movie.id) else viewModel.insert(movie)
                        isFavorite = !isFavorite
                        favoriteState()
                    }
                }
                loading_detail.visibility = View.GONE


            }
            "TvShow" -> {
                val tvShow = intent.getParcelableExtra<TvShow>(EXTRA_TV)
                tvShow?.let {
                    text_detail_title.text = it.name
                    text_detail_year.text = it.firstAirDate
                    text_overview.text = it.overview
                    text_rating.text = it.voteAverage.toString()
                    it.posterPath.let { it2 ->
                        Glide.with(this).load("https://image.tmdb.org/t/p/w500$it2")
                            .into(iv_detail_poster)
                    }
                    viewModelTvDb.getTvShowDb(tvShow.id)
                    add_fav.setOnClickListener {
                        if (isFavorite) viewModelTvDb.deleteTvShowDb(tvShow.id) else viewModelTvDb.insert(tvShow)
                        isFavorite = !isFavorite
                        favoriteState()
                    }
                }

                loading_detail.visibility = View.GONE

            }
        }

        iv_back.setOnClickListener {
            finish()
        }
    }
    fun datePicker(v: TextView) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                val dateFormat = SimpleDateFormat("dd-MMM-yyyy")
                c.set(year, monthOfYear, dayOfMonth)
                val dateString = dateFormat.format(c.time)
                v.setText(dateString)
            },
            year,
            month,
            day
        )
/*        Fitur untuk disable hari-hari sebelumnya
        dpd.getDatePicker().minDate = System.currentTimeMillis() - 1000*/
        dpd.show()
    }

    private fun favoriteState() {
        if (isFavorite){
            add_fav.setImageDrawable(getDrawable(R.drawable.ic_favorite_red))
        }else{
            add_fav.setImageDrawable(getDrawable(R.drawable.ic_favorite_border_red))
        }
    }


    private fun View.setMargin(marginTop: Int, marginLeft: Int) {
        val viewLayoutParams = this.layoutParams as ViewGroup.MarginLayoutParams
        viewLayoutParams.topMargin = marginTop
        viewLayoutParams.leftMargin = marginLeft
    }
}

