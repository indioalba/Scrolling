package com.example.scrolling.di.modules;

import com.example.scrolling.ui.list.view.ScrollingActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MyApplicationModule {
    @ContributesAndroidInjector
    abstract ScrollingActivity contributeActivityInjector();
}
