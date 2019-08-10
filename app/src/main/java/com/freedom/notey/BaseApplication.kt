package com.freedom.notey

import android.app.Application
import com.freedom.notey.db.persistentModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class BaseApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            printLogger()
            androidContext(this@BaseApplication)
            modules(persistentModule)
        }
    }
}