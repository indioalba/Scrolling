package com.example.scrolling.ui.list.view.viewmodel

import android.view.View
import androidx.lifecycle.*
import com.example.scrolling.ui.list.UserAdapter
import com.example.scrolling.ui.list.UserService
import com.example.scrolling.ui.list.model.User
import com.example.scrolling.ui.list.model.UserResponse
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class ScrollingViewModel @Inject constructor(private val userService: UserService,
                                             var adapter: UserAdapter) : ViewModel() {

    private var userList = MutableLiveData<List<User>>().apply {
        value = emptyList()
    }

    var loadingVisibility: MutableLiveData<Int> = MutableLiveData<Int>().apply {
        value = View.GONE
    }

    var errorVisibility: MutableLiveData<Int> = MutableLiveData<Int>().apply {
        value = View.GONE
    }

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        errorVisibility.value = View.GONE
        loadingVisibility.value = View.VISIBLE
        userService.fetchUserList(object : retrofit2.Callback<UserResponse>{
            override fun onResponse(call: Call<UserResponse>?, response: Response<UserResponse>?) {
                loadingVisibility.value = View.GONE
                userList.value = response?.body()?.userList ?: emptyList()
                adapter.setUserList(userList.value!!)
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<UserResponse>?, t: Throwable?) {
                loadingVisibility.value = View.GONE
                errorVisibility.value = View.VISIBLE
            }
        })
    }
}