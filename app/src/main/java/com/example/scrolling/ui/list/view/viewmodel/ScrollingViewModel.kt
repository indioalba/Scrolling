package com.example.scrolling.ui.list.view.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.scrolling.R
import com.example.scrolling.ui.list.UserAdapter
import com.example.scrolling.ui.list.UserService
import com.example.scrolling.ui.list.model.User
import com.example.scrolling.ui.list.model.UserResponse
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class ScrollingViewModel @Inject constructor(private val userService: UserService) : ViewModel(){

    var adapter : UserAdapter
    private var _userList = MutableLiveData<List<User>>().apply {
        value = null
    }

    val userList: LiveData<List<User>> by lazy {
        _userList
    }

    var loadingVisibility: MutableLiveData<Int> = MutableLiveData<Int>().apply {
        value = View.GONE
    }

    var errorVisibility: MutableLiveData<Int> = MutableLiveData<Int>().apply {
        value = View.GONE
    }

    init {
        fetchUsers()
        adapter = UserAdapter(R.layout.row_item, this)
    }

    private fun fetchUsers() {
        loadingVisibility.value = View.VISIBLE
        userService.fetchUserList(object : retrofit2.Callback<UserResponse>{
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                loadingVisibility.value = View.GONE
                _userList.value = response.body().userList
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