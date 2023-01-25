package com.linhtetko.themoviebookingapp.data.vos.moviedb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.linhtetko.themovieapp.data.vos.CollectionVO
import com.linhtetko.themoviebookingapp.persistance.typeconverters.movies.*

@Entity(tableName = "movies")
@TypeConverters(
    CollectionTypeConverter::class,
    GenreIdsTypeConverter::class,
    GenreListTypeConverter::class,
    ProductionCompaniesTypeConverter::class,
    ProductionCountriesTypeConverter::class,
    SpokenLanguageTypeConverter::class
)
data class MovieVO(
    @ColumnInfo(name = "adult")
    @SerializedName("adult")
    val adult: Boolean?,

    @ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path")
    val backdropPath: String?,

    @ColumnInfo(name = "genre_ids")
    @SerializedName("genre_ids")
    val genreIds: List<Int>?,

    @ColumnInfo(name = "id")
    @SerializedName("id")
    @PrimaryKey
    val id: Int = 0,

    @ColumnInfo(name = "original_language")
    @SerializedName("original_language")
    val originalLanguage: String?,

    @ColumnInfo(name = "original_title")
    @SerializedName("original_title")
    val originalTitle: String?,

    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    val overview: String?,

    @ColumnInfo(name = "popularity")
    @SerializedName("popularity")
    val popularity: Double?,

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    val posterPath: String?,

    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    val releaseDate: String?,

    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String?,

    @ColumnInfo(name = "video")
    @SerializedName("video")
    val video: Boolean?,

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    val voteAverage: Double?,

    @ColumnInfo(name = "vote_count")
    @SerializedName("vote_count")
    val voteCount: Int?,

    @ColumnInfo(name = "belongs_to_collection")
    @SerializedName("belongs_to_collection")
    val belongsToCollection: CollectionVO?,

    @ColumnInfo(name = "budget")
    @SerializedName("budget")
    val budget: Int?,

    @ColumnInfo(name = "genres")
    @SerializedName("genres")
    val genres: List<GenreVO>?,

    @ColumnInfo(name = "homepage")
    @SerializedName("homepage")
    val homepage: String?,

    @ColumnInfo(name = "imdb_id")
    @SerializedName("imdb_id")
    val imdbId: String?,

    @ColumnInfo(name = "production_companies")
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompaniesVO>?,

    @ColumnInfo(name = "production_countries")
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountriesVO>?,

    @ColumnInfo(name = "revenue")
    @SerializedName("revenue")
    val revenue: Int?,

    @ColumnInfo(name = "runtime")
    @SerializedName("runtime")
    val runtime: Int?,

    @ColumnInfo(name = "spoken_languages")
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguagesVO>?,

    @ColumnInfo(name = "status")
    @SerializedName("status")
    val status: String?,

    @ColumnInfo(name = "tagline")
    @SerializedName("tagline")
    val tagline: String?,

    @ColumnInfo(name = "type")
    var type: String?
) {

    fun setRatingBaseOnFiveStar(): Float = voteAverage?.div(2)?.toFloat() ?: 0.0f

    fun getGenresAsCommaSeparatedString(): String {
        return genres?.joinToString(", ") { it.name } ?: ""
    }

    fun getProductionCountriesAsCommaSeparatedString(): String {
        return productionCountries?.joinToString(", ") { it.name } ?: ""
    }
}

const val NOW_PLAYING = "now_playing"
const val UP_COMING = "up_coming"
