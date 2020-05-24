package com.example.scrolling.ui.list

import com.example.scrolling.api.RetrofitApi
import com.example.scrolling.ui.list.model.UserResponse
import retrofit2.Callback
import javax.inject.Inject


class UserService @Inject constructor() {

    fun fetchUserList(callback: Callback<UserResponse>) {

        RetrofitApi.getRetrofitInstance().getUsers().enqueue(callback)
    }
}