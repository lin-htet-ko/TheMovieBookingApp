package com.linhtetko.themoviebookingapp.data.vos.moviedb

import com.google.gson.annotations.SerializedName

data class ActorVO(
    @SerializedName("original_name")
    val originalName: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("adult")
    val adult: Boolean?,
    @SerializedName("gender")
    val gender: Int?,
    @SerializedName("known_for")
    val knownFor: List<MovieVO>?,
    @SerializedName("known_for_department")
    val knownForDepartment: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("popularity")
    val popularity: Double?,
    @SerializedName("profile_path")
    val profile_path: String?,
    @SerializedName("cast_id")
    val castId: Int?,
    @SerializedName("character")
    val character: String?,
    @SerializedName("credit_id")
    val creditId: String?,
    @SerializedName("order")
    val order: Int?
)
