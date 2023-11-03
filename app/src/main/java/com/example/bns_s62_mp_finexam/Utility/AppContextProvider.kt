package com.example.bns_s62_mp_finexam.Utility

import android.annotation.SuppressLint
import android.content.Context

class AppContextProvider private constructor() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        private val instance = AppContextProvider()

        fun getInstance(): AppContextProvider {
            return instance
        }
    }
    private var context: Context? = null

    fun initialize(appContext: Context) {
        context = appContext
    }

    fun getAppContext(): Context {
        if (context == null) {
            throw IllegalStateException("Context has not been initialized")
        }
        return context!!
    }
}
