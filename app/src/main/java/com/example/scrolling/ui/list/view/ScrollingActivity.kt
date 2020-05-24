package com.example.scrolling.ui.list.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.scrolling.R
import com.example.scrolling.application.BaseActivity
import com.example.scrolling.databinding.ActivityScrollingBinding
import com.example.scrolling.ui.list.NetworkApi
import com.example.scrolling.ui.list.view.viewmodel.ScrollingViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_scrolling.*
import javax.inject.Inject

class ScrollingActivity : BaseActivity() {

    @Inject
    lateinit var networkApi: NetworkApi

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: ScrollingViewModel = ViewModelProvider(this, viewModelFactory)[ScrollingViewModel::class.java]
        // DataBinding
        val binding : ActivityScrollingBinding = DataBindingUtil.setContentView(this, R.layout.activity_scrolling)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        lifecycle.addObserver(viewModel);
        setContentView(binding.root)

        setSupportActionBar(toolbar)


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
