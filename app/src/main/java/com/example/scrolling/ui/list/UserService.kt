package com.example.scrolling.ui.list

import android.util.Log
import com.example.scrolling.api.RetrofitApi
import com.example.scrolling.model.UserResponse
import retrofit2.Callback
import javax.inject.Inject


class UserService @Inject constructor(private val retrofitApi: RetrofitApi) {

    var page = 0
    fun fetchUserList(name: String, callback: Callback<UserResponse>, isFirstTime: Boolean = false) {
        if (isFirstTime) page = 0

        retrofitApi.apiInterface.getUsers(name, ++page).enqueue(callback)
        Log.i("UserService", "Page: $page")
        Log.i("UserService", "https://gorest.co.in/public-api/users?first_name=$name&page=$page&access-token=tK_kUKHZtvn9ESp6XS5Qms1HMVAzGgO7wERc")
    }
}