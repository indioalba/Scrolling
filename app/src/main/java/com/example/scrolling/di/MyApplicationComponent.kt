package com.example.scrolling.di

import com.example.scrolling.application.MyApplication
import com.example.scrolling.di.modules.ActivityInjectorModule
import com.example.scrolling.di.modules.MyApplicationModule
import com.example.scrolling.di.modules.RoomModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, MyApplicationModule::class, ActivityInjectorModule::class, ViewModelModule::class, RoomModule::class])
interface MyApplicationComponent : AndroidInjector<MyApplication?>