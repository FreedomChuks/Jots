package com.freedom.notey

import com.freedom.notey.di.DaggerAppComponents
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class BaseApplication : DaggerApplication(){

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponents.builder().application(this).build()
    }


}