package com.jeckso.donatehelp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class DonateApp : Application(){
    override fun onCreate() {
        super.onCreate()
        Timber.e("DONATE APP CREATE")
    }
}