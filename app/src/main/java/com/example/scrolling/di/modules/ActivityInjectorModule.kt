package com.example.scrolling.di.modules

import com.example.scrolling.ui.list.view.ScrollingActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityInjectorModule {

    @ContributesAndroidInjector
    abstract fun contributeScrollingActivityInjector(): ScrollingActivity
}