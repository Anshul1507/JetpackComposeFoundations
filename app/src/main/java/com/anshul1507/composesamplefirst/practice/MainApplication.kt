package com.anshul1507.composesamplefirst.practice

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        //Global entry point for your app process, initialize third-party libs here (SDKs, logging tools, etc)
    }
}