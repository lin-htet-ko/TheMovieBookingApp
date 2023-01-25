package com.linhtetko.themoviebookingapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.linhtetko.themoviebookingapp.data.vos.moviedb.GenreVO
import kotlinx.android.synthetic.main.view_holder_genre.view.*

class GenreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindData(genreVO: GenreVO) {
        itemView.cpMovieDetailChip.text = genreVO.name
    }
}