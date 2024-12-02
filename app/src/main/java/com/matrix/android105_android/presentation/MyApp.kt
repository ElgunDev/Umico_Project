package com.matrix.android105_android.presentation

import android.app.Application
import com.matrix.android105_android.tools.SessionManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp:Application(){
    override fun onCreate() {
        super.onCreate()
        SessionManager.init(applicationContext)
    }
}