package com.example.scrolling.di.modules

import com.example.scrolling.application.MyApplication
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@Component(modules = [AndroidInjectionModule::class, MyApplicationModule::class, ViewModelModule::class])
interface MyApplicationComponent : AndroidInjector<MyApplication?>