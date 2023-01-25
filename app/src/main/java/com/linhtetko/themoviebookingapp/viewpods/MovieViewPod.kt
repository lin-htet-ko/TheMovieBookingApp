package com.linhtetko.themoviebookingapp.viewpods

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.linhtetko.themoviebookingapp.adapters.MoviesAdapter
import com.linhtetko.themoviebookingapp.data.vos.moviedb.GenreVO
import com.linhtetko.themoviebookingapp.data.vos.moviedb.MovieVO
import com.linhtetko.themoviebookingapp.delegates.MovieItemDelegate
import kotlinx.android.synthetic.main.view_pod_movie.view.*

class MovieViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private var mMovieadapter: MoviesAdapter? = null


    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    fun setUpViewPod(title: String, delegate: MovieItemDelegate) {
        tvVpMovieTitle.tvVpMovieTitle.text = title
        setUpRecyclerview(delegate)
    }

    private fun setUpRecyclerview(delegate: MovieItemDelegate) {
        mMovieadapter = MoviesAdapter(delegate)
        rvVpMovie.adapter = mMovieadapter
    }

    fun setNewData(genres: List<GenreVO>, movies: List<MovieVO>) {
        mMovieadapter?.setNewData(genres, movies)
    }
}