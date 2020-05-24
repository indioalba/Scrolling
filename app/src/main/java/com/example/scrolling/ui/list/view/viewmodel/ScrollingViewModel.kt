package com.example.scrolling.ui.list.view.viewmodel

import android.view.View
import androidx.lifecycle.*
import com.example.scrolling.ui.list.UserAdapter
import com.example.scrolling.ui.list.UserService
import com.example.scrolling.ui.list.model.User
import com.example.scrolling.ui.list.model.UserResponse
import com.example.scrolling.ui.list.view.LifespanController
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class ScrollingViewModel @Inject constructor(
    private val userService: UserService,
    private val lifespanController: LifespanController,
    var adapter: UserAdapter) : ViewModel(), LifecycleObserver {

    private var userList = MutableLiveData<List<User>>().apply {
        value = emptyList()
    }

    private val userListObserver: Observer<List<User>> = Observer {
        adapter.setUserList(userList.value!!)
        adapter.notifyDataSetChanged()
    }

    var loadingVisibility: MutableLiveData<Int> = MutableLiveData<Int>().apply {
        value = View.GONE
    }

    var errorVisibility: MutableLiveData<Int> = MutableLiveData<Int>().apply {
        value = View.GONE
    }

    var speed: String = ""

    private var speedInSeconds: Long = DEFAULT_SPEED

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        fetchUsers()
        userList.observeForever(userListObserver)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() = lifespanController.startTask(userList, speedInSeconds)

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onStop() = lifespanController.stopTask()

    fun fetchUsers() {
        errorVisibility.value = View.GONE
        loadingVisibility.value = View.VISIBLE
        userService.fetchUserList(object : retrofit2.Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>?, response: Response<UserResponse>?) {
                loadingVisibility.value = View.GONE
                userList.value = response?.body()?.userList ?: emptyList()
                lifespanController.startTask(userList, speedInSeconds)
            }

            override fun onFailure(call: Call<UserResponse>?, t: Throwable?) {
                loadingVisibility.value = View.GONE
                errorVisibility.value = View.VISIBLE
            }
        })
    }

    fun updateSpeed() {
        if (speed.isNotEmpty() && speed.toLong() > 0) {
            speedInSeconds = speed.toLong() * 1000
            lifespanController.startTask(userList, speedInSeconds)
        } else {
            // display error message, value must be > 0
        }
    }

    companion object {
        private const val DEFAULT_SPEED = 2000L
    }
}