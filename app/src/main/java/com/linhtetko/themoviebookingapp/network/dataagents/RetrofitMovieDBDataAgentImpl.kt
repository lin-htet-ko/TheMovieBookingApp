package com.linhtetko.themoviebookingapp.network.dataagents

import com.linhtetko.themoviebookingapp.data.vos.moviedb.ActorVO
import com.linhtetko.themoviebookingapp.data.vos.moviedb.GenreVO
import com.linhtetko.themoviebookingapp.data.vos.moviedb.MovieVO
import com.linhtetko.themoviebookingapp.network.api.MovieDBApi
import com.linhtetko.themoviebookingapp.network.response.CreditResponse
import com.linhtetko.themoviebookingapp.network.response.GenreResponse
import com.linhtetko.themoviebookingapp.network.response.MovieListResponse
import com.linhtetko.themoviebookingapp.util.MDB_BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitMovieDBDataAgentImpl : MovieDBDataAgent {

    private var mMovieApi: MovieDBApi? = null

    init {
        val client = OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .callTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS).build()

        val retrofit = Retrofit.Builder()
            .baseUrl(MDB_BASE_URL).client(client).addConverterFactory(GsonConverterFactory.create())
            .build()

        mMovieApi = retrofit.create(MovieDBApi::class.java)

    }

    override fun getNowPlayingMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieApi?.getNowPlayingMovies()?.enqueue(
            object : Callback<MovieListResponse> {
                override fun onResponse(
                    call: Call<MovieListResponse>,
                    response: Response<MovieListResponse>
                ) {
                    if (response.isSuccessful) {
                        val movieList = response.body()?.results ?: listOf()
                        onSuccess(movieList)
                    } else {
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }
            }
        )
    }

    override fun getUpComingMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieApi?.getUpComingMovies()?.enqueue(
            object : Callback<MovieListResponse> {
                override fun onResponse(
                    call: Call<MovieListResponse>,
                    response: Response<MovieListResponse>
                ) {
                    if (response.isSuccessful) {
                        val movieList = response.body()?.results ?: listOf()
                        onSuccess(movieList)
                    } else {
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }
            }
        )
    }

    override fun getGenre(onSuccess: (List<GenreVO>) -> Unit, onFailure: (String) -> Unit) {
        mMovieApi?.getGenre()?.enqueue(
            object : Callback<GenreResponse> {
                override fun onResponse(
                    call: Call<GenreResponse>,
                    response: Response<GenreResponse>
                ) {
                    if (response.isSuccessful) {
                        val genreList = response.body()?.genres ?: listOf()
                        onSuccess(genreList)
                    } else {
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<GenreResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }
            }
        )
    }


    override fun getMovieDetail(
        movieId: Int,
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieApi?.getMovieDetail(movieId)?.enqueue(
            object : Callback<MovieVO> {
                override fun onResponse(
                    call: Call<MovieVO>,
                    response: Response<MovieVO>
                ) {
                    if (response.isSuccessful) {
                        val movieVO = response.body()
                        if (movieVO != null) {
                            onSuccess(movieVO)
                        }
                    } else {
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<MovieVO>, t: Throwable) {
                    onFailure(t.message ?: "")
                }
            }
        )
    }

    override fun getMovieCredit(
        movieId: Int,
        onSuccess: (Pair<List<ActorVO>, List<ActorVO>>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieApi?.getMovieCredit(movieId)?.enqueue(
            object : Callback<CreditResponse> {
                override fun onResponse(
                    call: Call<CreditResponse>,
                    response: Response<CreditResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            onSuccess(Pair(it.cast ?: listOf(), it.crew ?: listOf()))
                        }
                    } else {
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<CreditResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }
            }
        )
    }
}