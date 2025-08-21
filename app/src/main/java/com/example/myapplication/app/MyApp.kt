package com.example.myapplication.app

import android.app.Application
import com.example.myapplication.di.databaseModule
import com.example.myapplication.di.loadRepositories
import com.example.myapplication.di.loadUseCase
import com.example.myapplication.di.loadViewModel
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            if (BuildConfig.DEBUG) {
                androidLogger()
            }
            androidContext(this@MyApp)
            modules(loadViewModel, loadUseCase, loadRepositories, databaseModule)
        }
    }
}