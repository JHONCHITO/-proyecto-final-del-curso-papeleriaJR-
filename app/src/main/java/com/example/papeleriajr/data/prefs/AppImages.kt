package com.example.papeleriajr.data.prefs

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val DS_NAME = "app_images"
private val Context.imagesDataStore by preferencesDataStore(name = DS_NAME)

class AppImages(private val context: Context) {

    private val bannerKey = stringPreferencesKey("banner")
    private fun catKey(catId: String) = stringPreferencesKey("cat_$catId")

    val bannerFlow: Flow<String?> = context.imagesDataStore.data.map { prefs ->
        prefs[bannerKey]
    }

    fun categoryImageFlow(categoryId: String): Flow<String?> =
        context.imagesDataStore.data.map { prefs ->
            prefs[catKey(categoryId)]
        }

    suspend fun setBanner(uri: String) {
        context.imagesDataStore.edit { it[bannerKey] = uri }
    }

    suspend fun setCategoryImage(categoryId: String, uri: String) {
        context.imagesDataStore.edit { it[catKey(categoryId)] = uri }
    }
}
