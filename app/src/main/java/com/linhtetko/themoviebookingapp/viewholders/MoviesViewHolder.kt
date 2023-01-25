package com.linhtetko.themoviebookingapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.linhtetko.themoviebookingapp.data.vos.moviedb.GenreVO
import com.linhtetko.themoviebookingapp.data.vos.moviedb.MovieVO
import com.linhtetko.themoviebookingapp.delegates.MovieItemDelegate
import com.linhtetko.themoviebookingapp.util.loadImageFromMBA
import com.linhtetko.themoviebookingapp.util.loadImageFromMDB
import kotlinx.android.synthetic.main.view_holder_movie.view.*

class MoviesViewHolder(itemView: View,private val delegate: MovieItemDelegate) : RecyclerView.ViewHolder(itemView) {


    fun bindData(movieVO: MovieVO, genres: List<GenreVO>){
        movieVO.backdropPath?.let { itemView.ivVhMovieImg.loadImageFromMDB(it) }
        movieVO.title?.let { itemView.tvVhMovieName.text = it }
        movieVO.genreIds?.let {
            val tempGenres = mutableListOf<String>()
            it.map { id ->
                tempGenres.addAll(genres.filter { genre -> id == genre.id }.map { genre -> genre.name })
            }
            itemView.tvVhMovieGenre.text = tempGenres.joinToString("/ ")
        }
        movieVO.releaseDate?.let { itemView.tvVhMovieDuration.text = it }

        itemView.setOnClickListener { delegate.onClickItem(movieVO.id) }
    }
}