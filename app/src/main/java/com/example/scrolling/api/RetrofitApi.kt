package com.example.scrolling.api

import com.example.scrolling.ui.list.model.UserResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.lang.Exception
import java.lang.IllegalArgumentException


class RetrofitApi {

    // Access Token: tK_kUKHZtvn9ESp6XS5Qms1HMVAzGgO7wERc
    // https://gorest.co.in/

    companion object {
        private var api: ApiInterface? = null
        private const val BASE_URL = "https://gorest.co.in"

        fun getRetrofitInstance(): ApiInterface {
            if (api == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                api = retrofit.create(ApiInterface::class.java)
            }
            return api ?: throw IllegalArgumentException("Retrofit ApiInterface is null")
        }
    }

    interface ApiInterface {

        @GET("/public-api/users?access-token=tK_kUKHZtvn9ESp6XS5Qms1HMVAzGgO7wERc")
        fun getUsers(): Call<UserResponse>

    }
}