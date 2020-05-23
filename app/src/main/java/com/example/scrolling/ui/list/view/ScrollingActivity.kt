package com.example.scrolling.ui.list.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.scrolling.R
import com.example.scrolling.application.BaseActivity
import com.example.scrolling.databinding.ActivityScrollingBinding
import com.example.scrolling.ui.list.NetworkApi
import com.example.scrolling.ui.list.model.User
import com.example.scrolling.ui.list.view.viewmodel.ScrollingViewModel
import kotlinx.android.synthetic.main.activity_scrolling.*
import javax.inject.Inject


// Access Token: tK_kUKHZtvn9ESp6XS5Qms1HMVAzGgO7wERc
// https://gorest.co.in/

class ScrollingActivity : BaseActivity() {

    @Inject
    lateinit var networkApi: NetworkApi

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    //private val viewModel: ScrollingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_scrolling)
        setSupportActionBar(toolbar)

        // ViewModel
        val viewModel: ScrollingViewModel = ViewModelProvider(this, viewModelFactory)[ScrollingViewModel::class.java]
        // DataBinding
        val binding : ActivityScrollingBinding = DataBindingUtil.setContentView(this, R.layout.activity_scrolling)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setContentView(binding.root)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        // test livedata, instance and update when when userResponse get updated
        val userResponse = Observer<List<User>> { userList ->
            viewModel.updateUserList(userList)
        }
        // observing in userResponse the updates of viewModel.userResponse
        viewModel.userList.observe(this, userResponse)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
