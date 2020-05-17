package com.example.scrolling.ui.list.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.viewModels

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.scrolling.R
import com.example.scrolling.application.BaseActivity
import com.example.scrolling.ui.list.NetworkApi
import com.example.scrolling.ui.list.model.UserResponse
import com.example.scrolling.ui.list.view.viewmodel.ScrollingViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_scrolling.*
import java.util.EnumSet.of
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
        setContentView(R.layout.activity_scrolling)
        setSupportActionBar(toolbar)
        val viewModel: ScrollingViewModel = ViewModelProviders.of(this, viewModelFactory)[ScrollingViewModel::class.java]

        val injected = if (networkApi == null) false else true
        val userAvailable = findViewById(R.id.target) as TextView
        userAvailable.text = "Dependency injection worked: $injected"

        val userResponse = Observer<UserResponse> {
            userAvailable.text = it.userList[0].firstName
        }

        viewModel.userResponse.observe(this, userResponse)
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
