package com.example.scrolling.ui.list.model

import com.google.gson.annotations.SerializedName

data class UserResponse(@SerializedName("_meta") val meta: Meta, @SerializedName("result") val userList: List<User>)

data class Meta(val success:Boolean,
                val code:Int,
                val message: String,
                val totalCount: Int,
                val pageCount:Int,
                val currentPage: Int,
                val perPage: Int,
                val rateLimit: RateLimit)

data class RateLimit(val limit:Int,
                     val remaining:Int,
                     val reset:Int)

data class User(val id: Int,
                @SerializedName("first_name") val firstName: String,
                @SerializedName("last_name") val lastName: String,
                val gender: Gender,
                val dob: String,
                val email: String,
                val phone: String,
                val website: String,
                val address: String,
                val status: Status)

enum class Gender(val value: String){
 MALE("male"),
 FEMALE("female")
}

enum class Status(val status: String) {
 ACTIVE("active"),
 INACTIVE("inactive")
}
