package com.example.scrolling.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("_meta") val meta: Meta,
    @SerializedName("result") val userList: List<User>)

data class Meta(
    val success: Boolean,
    val code: Int,
    val message: String,
    val totalCount: Int,
    val pageCount: Int,
    val currentPage: Int,
    val perPage: Int,
    val rateLimit: RateLimit)

data class RateLimit(
    val limit: Int,
    val remaining: Int,
    val reset: Int)