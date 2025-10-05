package com.nnk.appkira.core.storage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlin.collections.get

interface AppPreferences {
    suspend fun getShowSpecialAppsPref(): Set<String>

    suspend fun setShowSpecialAppsPref(specialApps: Set<String>)

    companion object {
        fun getInstance(preferences: DataStore<Preferences>): AppPreferences = AppPreferencesImpl(preferences)
    }
}

private class AppPreferencesImpl(
    private val preferences: DataStore<Preferences>,
) : AppPreferences {
    override suspend fun getShowSpecialAppsPref(): Set<String> =
        preferences.data
            .map { prefs ->
                prefs[KEY_SHOW_SPECIAL_APPS_PREF] ?: emptySet()
            }.first()

    override suspend fun setShowSpecialAppsPref(specialApps: Set<String>) {
        preferences.edit { prefs ->
            prefs[KEY_SHOW_SPECIAL_APPS_PREF] = specialApps
        }
    }

    companion object {
        private val KEY_SHOW_SPECIAL_APPS_PREF = stringSetPreferencesKey("show_special_apps_pref")
    }
}
