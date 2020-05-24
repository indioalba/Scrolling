package com.example.scrolling.ui.list.view

import androidx.lifecycle.MutableLiveData
import com.example.scrolling.ui.list.model.User
import java.util.*
import javax.inject.Inject

class LifespanController @Inject constructor(){
    var timerTaskObj: TimerTask? = null
    var timerObj : Timer? = null

    fun startTask(userList: MutableLiveData<List<User>>, lifespanTime: Long = 1500) {
        stopTask()

        timerObj = Timer()
        timerTaskObj = object : TimerTask() {
            override fun run() {
                val tempUserList = userList.value!!
                if(!tempUserList.isNullOrEmpty()) {
                    userList.postValue(tempUserList.subList(1, tempUserList.size))
                } else {
                    timerObj?.cancel()
                    timerObj?.purge()
                }
            }
        }

        timerObj?.schedule(timerTaskObj, lifespanTime, lifespanTime)
    }

    fun stopTask(){
        timerTaskObj?.cancel()
        timerObj?.cancel()
        timerObj?.purge()
    }
}