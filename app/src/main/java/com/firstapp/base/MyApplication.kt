package com.firstapp.base

import android.app.Application
import android.content.Context
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.soloader.SoLoader
import com.firstapp.BuildConfig


class MyApplication : Application() {

   init {
       instance = this
   }

 companion object {
        private var instance: MyApplication? = null

        fun getAppContext(): Context {
            return instance!!.applicationContext
        }
    }
    override fun onCreate() {
        super.onCreate()
        val mContext: Context  = MyApplication.getAppContext()
        SoLoader.init(this, false)

        if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(this)) {
            val client = AndroidFlipperClient.getInstance(this)
            client.addPlugin(InspectorFlipperPlugin(this, DescriptorMapping.withDefaults()))
            client.start()
        }

    }



}