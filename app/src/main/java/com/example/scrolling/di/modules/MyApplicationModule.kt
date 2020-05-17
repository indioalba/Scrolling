package com.example.scrolling.di.modules

import com.example.scrolling.ui.list.view.ScrollingActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module
internal abstract class MyApplicationModule {

    @Singleton
    @ContributesAndroidInjector
    abstract fun contributeScrollingActivityInjector(): ScrollingActivity
}