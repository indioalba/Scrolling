package com.example.scrolling.ui.list.view

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.scrolling.R
import com.example.scrolling.application.BaseActivity
import com.example.scrolling.databinding.ActivityScrollingBinding
import com.example.scrolling.ui.list.view.viewmodel.ScrollingViewModel
import kotlinx.android.synthetic.main.activity_scrolling.*
import javax.inject.Inject

class ScrollingActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: ScrollingViewModel =
            ViewModelProvider(this, viewModelFactory)[ScrollingViewModel::class.java]
        // DataBinding
        val binding: ActivityScrollingBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_scrolling)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        lifecycle.addObserver(viewModel);
        setContentView(binding.root)
        setSupportActionBar(toolbar)
    }
}
