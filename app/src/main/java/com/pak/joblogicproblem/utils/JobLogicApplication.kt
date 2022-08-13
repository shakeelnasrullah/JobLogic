package com.pak.joblogicproblem.utils

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class JobLogicApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}