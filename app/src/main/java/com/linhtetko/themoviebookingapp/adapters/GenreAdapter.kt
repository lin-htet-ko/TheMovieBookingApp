package com.linhtetko.themoviebookingapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.linhtetko.themoviebookingapp.R
import com.linhtetko.themoviebookingapp.data.vos.moviedb.GenreVO
import com.linhtetko.themoviebookingapp.data.vos.moviedb.MovieVO
import com.linhtetko.themoviebookingapp.viewholders.GenreViewHolder

class GenreAdapter : RecyclerView.Adapter<GenreViewHolder>() {

    private var genres: List<GenreVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_genre, parent, false)
        return GenreViewHolder(view)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        if(genres.isNotEmpty()){
            holder.bindData(genres[position])
        }
    }

    override fun getItemCount(): Int = genres.size

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(genresVOs: List<GenreVO>) {
        genres = genresVOs
        notifyDataSetChanged()
    }
}