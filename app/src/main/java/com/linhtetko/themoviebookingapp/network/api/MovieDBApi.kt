package com.linhtetko.themoviebookingapp.network.api

import com.linhtetko.themoviebookingapp.data.vos.moviedb.MovieVO
import com.linhtetko.themoviebookingapp.network.response.CreditResponse
import com.linhtetko.themoviebookingapp.network.response.GenreResponse
import com.linhtetko.themoviebookingapp.network.response.MovieListResponse
import com.linhtetko.themoviebookingapp.util.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDBApi {

    @GET(MDB_GET_NOW_PLAYING)
    fun getNowPlayingMovies(
        @Query(PARAM_API_KEY) apiKey: String = MDB_API_KEY,
        @Query(PARAM_PAGE) page: Int = 1,
    ): Call<MovieListResponse>

    @GET(MDB_GET_UPCOMING)
    fun getUpComingMovies(
        @Query(PARAM_API_KEY) apiKey: String = MDB_API_KEY,
        @Query(PARAM_PAGE) page: Int = 1,
    ): Call<MovieListResponse>

    @GET(MDB_GET_GENRE)
    fun getGenre(
        @Query(PARAM_API_KEY) apiKey: String = MDB_API_KEY
    ): Call<GenreResponse>

    @GET(MDB_GET_MOVIE_DETAIL)
    fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query(PARAM_API_KEY) apiKey: String = MDB_API_KEY
    ): Call<MovieVO>

    @GET(MDB_GET_MOVIE_CREDIT)
    fun getMovieCredit(
        @Path("movie_id") movieId: Int,
        @Query(PARAM_API_KEY) apiKey: String = MDB_API_KEY
    ): Call<CreditResponse>


}