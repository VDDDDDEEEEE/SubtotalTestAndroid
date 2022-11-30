package com.test.subtotal

import android.app.Application
import android.content.Context


class App : Application() {

    private var mContext: Context? = null

    override fun onCreate() {
        super.onCreate()
        mContext = this
    }

    fun getContext(): Context? {
        return mContext
    }

}