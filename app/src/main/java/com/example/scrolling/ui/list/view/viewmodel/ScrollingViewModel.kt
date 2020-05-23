package com.example.scrolling.ui.list.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.scrolling.ui.list.UserService

import com.example.scrolling.ui.list.model.UserResponse
import javax.inject.Inject

class ScrollingViewModel @Inject constructor(private val userService: UserService) : ViewModel() {

    val name ="Matias Prat"

    private var _userResponse = MutableLiveData<UserResponse>().apply {
        value = null
    }

    val userResponse: LiveData<UserResponse> by lazy {
        _userResponse
    }

    init {
        _userResponse.value = userService.fetchUserList()
    }

}