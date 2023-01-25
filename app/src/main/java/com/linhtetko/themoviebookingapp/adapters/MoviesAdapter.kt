package com.linhtetko.themoviebookingapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.linhtetko.themoviebookingapp.R
import com.linhtetko.themoviebookingapp.data.vos.moviedb.GenreVO
import com.linhtetko.themoviebookingapp.data.vos.moviedb.MovieVO
import com.linhtetko.themoviebookingapp.delegates.MovieItemDelegate
import com.linhtetko.themoviebookingapp.viewholders.MoviesViewHolder

class MoviesAdapter(
    private val movieItemDelegate: MovieItemDelegate,
) : RecyclerView.Adapter<MoviesViewHolder>() {

    private var movies: Pair<List<MovieVO>, List<GenreVO>> = listOf<MovieVO>() to listOf<GenreVO>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie, parent, false)
        return MoviesViewHolder(view, movieItemDelegate)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        if (movies.first.isNotEmpty()) {
            holder.bindData(movies.first[position], movies.second)
        }
    }

    override fun getItemCount(): Int = movies.first.size


    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(genres: List<GenreVO>, items: List<MovieVO>) {
        movies = items to genres
        notifyDataSetChanged()
    }
}