package com.example.scrolling.ui.list.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.scrolling.R
import com.example.scrolling.ui.list.UserAdapter
import com.example.scrolling.ui.list.UserService
import com.example.scrolling.ui.list.model.User

import javax.inject.Inject

class ScrollingViewModel @Inject constructor(private val userService: UserService) : ViewModel() {

    val name ="Matias Prat"
    lateinit var adapter : UserAdapter

    private var _userList = MutableLiveData<List<User>>().apply {
        value = null
    }

    val userList: LiveData<List<User>> by lazy {
        _userList
    }

    init {
        _userList.value = userService.fetchUserList()
        adapter = UserAdapter(R.layout.row_item, this)
    }

    fun updateUserList(userList: List<User>) {
        adapter.setUserList(userList)
    }

}