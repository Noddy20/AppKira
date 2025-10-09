package com.nnk.appkira.core.storage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import com.nnk.appkira.domain.model.AppForceStopMode
import com.nnk.appkira.domain.model.AutoForceStopDelay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

interface AppPreferences {
    suspend fun getShowSpecialAppsPref(): Set<String>

    suspend fun setShowSpecialAppsPref(specialApps: Set<String>)

    suspend fun getAppForceStopModePref(packageName: String): String

    suspend fun setAppForceStopModePref(
        appPackageName: String,
        mode: String,
    )

    suspend fun getAutoForceStopDelayInMillis(): Long

    suspend fun setAutoForceStopDelay(delayInMillis: Long)

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

    override suspend fun getAppForceStopModePref(packageName: String): String =
        preferences.data
            .map { prefs ->
                prefs[keyAppForceStopModePref(packageName)] ?: AppForceStopMode.Never.modeName
            }.first()

    override suspend fun setAppForceStopModePref(
        appPackageName: String,
        mode: String,
    ) {
        preferences.edit { prefs ->
            prefs[keyAppForceStopModePref(appPackageName)] = mode
        }
    }

    override suspend fun getAutoForceStopDelayInMillis(): Long =
        preferences.data
            .map { prefs ->
                prefs[KEY_AUTO_FORCE_STOP_DELAY_PREF] ?: AutoForceStopDelay.ONE_WEEK.delayInMillis
            }.first()

    override suspend fun setAutoForceStopDelay(delayInMillis: Long) {
        preferences.edit { prefs ->
            prefs[KEY_AUTO_FORCE_STOP_DELAY_PREF] = delayInMillis
        }
    }

    companion object {
        private val KEY_SHOW_SPECIAL_APPS_PREF = stringSetPreferencesKey("show_special_apps_pref")
        private val KEY_AUTO_FORCE_STOP_DELAY_PREF = longPreferencesKey("auto_force_stop_delay_pref")

        private fun keyAppForceStopModePref(packageName: String) = stringPreferencesKey("$packageName-app_force_stop_mode_pref")
    }
}
