package com.mikolove.allmight

import android.app.Application
import androidx.databinding.DataBindingUtil
import com.facebook.stetho.Stetho
import timber.log.Timber

class ApplicationController : Application(){

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
        DataBindingUtil.setDefaultComponent(BindingComponent())
    }
}