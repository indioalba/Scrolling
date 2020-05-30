package com.example.scrolling.ui.list.view.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.*
import com.example.scrolling.model.User
import com.example.scrolling.model.UserResponse
import com.example.scrolling.ui.list.UserAdapter
import com.example.scrolling.ui.list.UserService
import com.example.scrolling.ui.list.view.LifespanController
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class ScrollingViewModel @Inject constructor(
    private val userService: UserService,
    private val lifespanController: LifespanController,
    var adapter: UserAdapter
) : ViewModel(), LifecycleObserver {

    private var speedInMiliseconds: Long = DEFAULT_SPEED

    var speed: String = ""
    var name: String = ""

    private var userList = MutableLiveData<List<User>>(emptyList())

    private val userListObserver: Observer<List<User>> = Observer {
        adapter.setUserList(userList.value)
        adapter.notifyDataSetChanged()
    }

    var loadingVisibility: MutableLiveData<Int> = MutableLiveData<Int>(View.GONE)

    val loadingEndOfListVisibility: MutableLiveData<Int> = MutableLiveData<Int>(View.GONE)

    var errorVisibility: MutableLiveData<Int> = MutableLiveData<Int>(View.GONE)

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        userList.observeForever(userListObserver)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() = lifespanController.startTask(userList, speedInMiliseconds)

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onStop() = lifespanController.stopTask()

    fun fetchUsers() {
        errorVisibility.value = View.GONE
        loadingVisibility.value = View.VISIBLE
        userList.value = emptyList()
        userService.fetchUserList(name, callback, true)
    }

    fun onEndOfList() {
        if (loadingEndOfListVisibility.value == View.GONE) {
            loadingEndOfListVisibility.value = View.VISIBLE
            userService.fetchUserList(name, callback)
        }
    }

    private val callback = object : retrofit2.Callback<UserResponse> {
        override fun onResponse(call: Call<UserResponse>?, response: Response<UserResponse>?) {
            userList.value = userList.value?.plus(response?.body()?.userList ?: emptyList())
            lifespanController.startTask(userList, speedInMiliseconds)
            loadingVisibility.value = View.GONE
            loadingEndOfListVisibility.value = View.GONE
        }

        override fun onFailure(call: Call<UserResponse>?, t: Throwable?) {
            errorVisibility.value = View.VISIBLE
            loadingVisibility.value = View.GONE
            loadingEndOfListVisibility.value = View.GONE
        }
    }

    fun updateSpeed() {
        if (speed.isNotEmpty() && speed.toLong() > 0) {
            speedInMiliseconds = speed.toLong() * 1000
            lifespanController.startTask(userList, speedInMiliseconds)
        } else {
            Log.e("ScrollingViewModel", "Speed value must be > 0")
        }
    }

    companion object {
        private const val DEFAULT_SPEED = 3000L
    }
}