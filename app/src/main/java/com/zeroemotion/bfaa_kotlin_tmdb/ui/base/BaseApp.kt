package com.zeroemotion.bfaa_kotlin_tmdb.ui.base

import android.app.Application
import com.zeroemotion.bfaa_kotlin_tmdb.di.module
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@BaseApp)
            modules(module)
        }
    }
}