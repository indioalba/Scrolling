package com.example.scrolling.api

import com.example.scrolling.model.UserResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitApi @Inject constructor() {

    val apiInterface: ApiInterface = getRetrofitInstance()

    private fun getRetrofitInstance(): ApiInterface {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ApiInterface::class.java)
    }

    companion object {
        private const val BASE_URL = "https://gorest.co.in"
    }

    interface ApiInterface {

        @GET("/public-api/users")
        fun getUsers(
            @Query("first_name") firstName: String = "",
            @Query("page") page: Int = 1,
            @Query("access-token") id: String = "tK_kUKHZtvn9ESp6XS5Qms1HMVAzGgO7wERc"
        ): Call<UserResponse>

    }
}