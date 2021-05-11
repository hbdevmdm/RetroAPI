package com.dc.mvvmskeleton

import android.app.Application
import android.content.Context
import com.facebook.stetho.Stetho


/**
 * Created by HB on 21/8/19.
 */
class MainApplication : Application() {


    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        Stetho.initializeWithDefaults(this)
        /*Logger.initializeSession(this)*/
    }

}
