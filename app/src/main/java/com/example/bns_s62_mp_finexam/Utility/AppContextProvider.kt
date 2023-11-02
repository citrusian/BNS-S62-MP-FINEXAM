package com.example.bns_s62_mp_finexam.Utility

import android.content.Context
import java.lang.ref.WeakReference

//object AppContextProvider {
//    private var contextRef: WeakReference<Context>? = null
//
//    fun initialize(applicationContext: Context) {
//        contextRef = WeakReference(applicationContext)
//    }
//
//    fun getAppContext(): Context? {
//        return contextRef?.get()
//    }
//}


object AppContextProvider {
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
