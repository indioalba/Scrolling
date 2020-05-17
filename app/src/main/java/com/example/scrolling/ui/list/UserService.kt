package com.example.scrolling.ui.list

import com.example.scrolling.ui.list.model.Gender
import com.example.scrolling.ui.list.model.User
import com.example.scrolling.ui.list.model.UserResponse
import javax.inject.Inject


class UserService @Inject constructor() {

    fun fetchUserList() : UserResponse {
        val userResponse = UserResponse(listOf(User(1, "Manuel A.", "Reyes", Gender.MALE), User(2, "Rosa", "Valverde", Gender.FEMALE)))
        return userResponse
    }
}