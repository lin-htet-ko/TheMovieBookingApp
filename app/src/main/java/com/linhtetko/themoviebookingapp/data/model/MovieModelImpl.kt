package com.linhtetko.themoviebookingapp.data.model

import android.util.Log
import androidx.room.Room
import com.linhtetko.themoviebookingapp.data.model.MovieBookingModelImpl.mMovieDatabase
import com.linhtetko.themoviebookingapp.data.vos.moviedb.*
import com.linhtetko.themoviebookingapp.network.dataagents.MovieDBDataAgent
import com.linhtetko.themoviebookingapp.network.dataagents.RetrofitMovieDBDataAgentImpl
import com.linhtetko.themoviebookingapp.persistance.MovieBookingDatabase
import com.linhtetko.themoviebookingapp.persistance.daos.MovieDAO

object MovieModelImpl : MovieModel {

    private val mMovieDataAgent: MovieDBDataAgent = RetrofitMovieDBDataAgentImpl

    var mMovieDatabase: MovieBookingDatabase? = MovieBookingModelImpl.mMovieDatabase

    override fun getNowPlayingMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        onSuccess(
            MovieBookingModelImpl.mMovieDatabase?.movieDao()?.getMovieByType(NOW_PLAYING) ?: emptyList()
        )

        mMovieDataAgent.getNowPlayingMovies(onSuccess = {
            it.forEach { movieVO ->
                movieVO.type = NOW_PLAYING
            }

            mMovieDatabase?.movieDao()?.insertMovies(it)

            onSuccess(it)
        }, onFailure)
    }

    override fun getUpComingMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        onSuccess(
            mMovieDatabase?.movieDao()?.getMovieByType(UP_COMING) ?: emptyList()
        )
        mMovieDataAgent.getUpComingMovies(onSuccess = {
            it.forEach { movieVO ->
                movieVO.type = UP_COMING
            }

            mMovieDatabase?.movieDao()?.insertMovies(it)
            onSuccess(it)
        }, onFailure)
    }

    override fun getGenreList(onSuccess: (List<GenreVO>) -> Unit, onFailure: (String) -> Unit) {

        onSuccess(mMovieDatabase?.genreDao()?.getGenres() ?: emptyList())

        mMovieDataAgent.getGenre(onSuccess = {
            mMovieDatabase?.genreDao()?.insertGenres(it)

            onSuccess(it)
        }, onFailure)
    }

    override fun getMovieDetail(
        movieId: Int,
        onSuccess: (MovieVO?) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val movieFromDb = mMovieDatabase?.movieDao()?.getMovieById(movieId)
        onSuccess(movieFromDb)

        mMovieDataAgent.getMovieDetail(movieId, onSuccess = {
            it.type = movieFromDb?.type
            mMovieDatabase?.movieDao()?.insertMovie(it)

            onSuccess(it)
        }, onFailure)
    }

    override fun getMovieCredit(
        movieId: Int,
        onSuccess: (Pair<List<ActorVO>, List<ActorVO>>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieDataAgent.getMovieCredit(movieId, onSuccess, onFailure)
    }
}