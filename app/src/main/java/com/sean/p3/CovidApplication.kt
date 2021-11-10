package com.sean.p3

import android.app.Application

class CovidApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        ObjectBox.init(this)
    }
}