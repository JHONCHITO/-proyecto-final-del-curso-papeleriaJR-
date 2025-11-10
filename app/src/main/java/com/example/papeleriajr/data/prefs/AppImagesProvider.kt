package com.example.papeleriajr.data.prefs

import android.content.Context

object AppImagesProvider {
    @Volatile private var instance: AppImages? = null
    fun get(context: Context): AppImages =
        instance ?: synchronized(this) {
            instance ?: AppImages(context.applicationContext).also { instance = it }
        }
}
