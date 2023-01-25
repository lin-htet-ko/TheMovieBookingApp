package com.linhtetko.themoviebookingapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.linhtetko.themoviebookingapp.R
import com.linhtetko.themoviebookingapp.adapters.CastAdapter
import com.linhtetko.themoviebookingapp.adapters.GenreAdapter
import com.linhtetko.themoviebookingapp.data.model.MovieModel
import com.linhtetko.themoviebookingapp.data.model.MovieModelImpl
import com.linhtetko.themoviebookingapp.data.vos.moviedb.MovieVO
import com.linhtetko.themoviebookingapp.util.loadImageFromMDB
import com.linhtetko.themoviebookingapp.util.showSnackBar
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity(R.layout.activity_movie_detail) {

    private val mMovieDBModel: MovieModel = MovieModelImpl
    private lateinit var genreAdapter: GenreAdapter
    private lateinit var mCastAdapter: CastAdapter

    companion object {

        private const val IE_MOVIE = "movie_id"
        var movieId : Int? = null

        fun newIntent(context: Context, movieId: Int): Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(IE_MOVIE, movieId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpActionbar()
        setUpGenre()
        setUpCasts()
        setUpListener()

        getMovieVO()
    }

    private fun getMovieVO() {
        movieId = intent.getIntExtra(IE_MOVIE, 0)
        mMovieDBModel.getMovieDetail(
            movieId = movieId!!,
            onSuccess = {
                bindData(it)
            },
            onFailure = {
                showErrorMessage(it)
            }
        )
    }

    private fun bindData(movieVO: MovieVO?) {
        movieVO?.apply {
            if (posterPath != null) {
                ivMovieDetailContentImg.loadImageFromMDB(posterPath)
            }
            tbMovieDetail.title = title ?: ""
            tvMovieDetailTitle.text = title ?: ""
            tvMovieDetailDuration.text = releaseDate ?: ""
            rbMovieDetailRating.rating = movieVO.setRatingBaseOnFiveStar()
            tvMovieDetailImdb.text = imdbId ?: ""


            genreAdapter.setNewData(genres ?: listOf())
            tvMovieDetailPlot.text = overview ?: ""

            getCredits(id)
        }
    }

    private fun getCredits(movieId: Int) {
        mMovieDBModel.getMovieCredit(
            movieId = movieId,
            onSuccess = {
                mCastAdapter.setNewData(it.second)
            },
            onFailure = {
                showErrorMessage(it)
            }
        )
    }

    private fun setUpGenre() {
        genreAdapter = GenreAdapter()
        rvMovieDetailGenre.adapter = genreAdapter
    }

    private fun setUpListener() {
        btnMovieDetailGetTicket.setOnClickListener {
            startActivity(
                ChooseCinemaAndTimeActivity.newIntent(
                    this
                )
            )
        }

        ivMovieDetailContentImg.setOnClickListener {
            super.onBackPressed()
        }
    }

    private fun showErrorMessage(message: String) {
        clMovieDetail.showSnackBar(message)
    }

    private fun setUpCasts() {
        mCastAdapter = CastAdapter()
        rvMovieDetailCasts.adapter = mCastAdapter
    }

    private fun setUpActionbar() {
        setSupportActionBar(tbMovieDetail)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_chevron_left_24dp)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) super.onBackPressed()
        return super.onOptionsItemSelected(item)
    }

}