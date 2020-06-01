package com.example.scrolling.di.modules

import android.content.Context
import com.example.scrolling.application.MyApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MyApplicationModule() {

        @Provides
        @Singleton
        fun providesContext(application: MyApplication): Context {
            return application.applicationContext
        }
}