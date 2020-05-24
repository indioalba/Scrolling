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
    var adapter: UserAdapter,
    private val lifespanController: LifespanController
) : ViewModel(), LifecycleObserver {

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

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        fetchUsers()
        userList.observeForever(userListObserver)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() = lifespanController.startTask(userList, 1000)

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onStop() = lifespanController.stopTask()

    fun fetchUsers() {
        errorVisibility.value = View.GONE
        loadingVisibility.value = View.VISIBLE
        userService.fetchUserList(object : retrofit2.Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>?, response: Response<UserResponse>?) {
                loadingVisibility.value = View.GONE
                userList.value = response?.body()?.userList ?: emptyList()
                lifespanController.startTask( userList, 1000)
            }

            override fun onFailure(call: Call<UserResponse>?, t: Throwable?) {
                loadingVisibility.value = View.GONE
                errorVisibility.value = View.VISIBLE
            }
        })
    }
}