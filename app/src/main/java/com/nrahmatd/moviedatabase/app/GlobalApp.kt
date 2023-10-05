package com.nrahmatd.moviedatabase.app

import android.app.Application
import android.content.Context

class GlobalApp : Application() {

    companion object {
        private lateinit var mAppContext: Context

        @get:Synchronized
        lateinit var instance: GlobalApp

        private fun setAppContext(ctx: Context) {
            mAppContext = ctx
        }

        fun getAppContext(): Context {
            return mAppContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        setAppContext(this)
        instance = this
    }
}
