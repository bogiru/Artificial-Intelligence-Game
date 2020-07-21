package com.example.artificialintelligencegame

import androidx.multidex.MultiDexApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import com.example.artificialintelligencegame.di.modules

class App : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(modules)
        }
    }

}