package com.linhtetko.themoviebookingapp.data.model

import com.linhtetko.themoviebookingapp.data.vos.moviedb.ActorVO
import com.linhtetko.themoviebookingapp.data.vos.moviedb.GenreVO
import com.linhtetko.themoviebookingapp.data.vos.moviedb.MovieVO

interface MovieModel {

    fun getNowPlayingMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getUpComingMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    )


    fun getGenreList(
        onSuccess: (List<GenreVO>) -> Unit,
        onFailure: (String) -> Unit
    )


    fun getMovieDetail(
        movieId: Int,
        onSuccess: (MovieVO?) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getMovieCredit(
        movieId: Int,
        onSuccess: (Pair<List<ActorVO>, List<ActorVO>>) -> Unit,
        onFailure: (String) -> Unit
    )
}