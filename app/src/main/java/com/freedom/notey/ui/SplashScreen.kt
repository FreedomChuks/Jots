package com.freedom.notey.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.freedom.notey.R
import com.freedom.notey.ui.main.MainActivity
import com.freedom.notey.utils.launchActivity

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        launchActivity<MainActivity> { finish() }
    }
}
