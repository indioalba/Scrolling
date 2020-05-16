package com.example.scrolling.ui.list

import javax.inject.Inject

class NetworkApi @Inject constructor(){

    fun validateUser(username: String?, password: String?): Boolean {
        // imagine an actual network call here
        // for demo purpose return false in "real" life
        return !(username == null || username.isEmpty())
    }

}
