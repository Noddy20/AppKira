package com.nnk.appkira.data.features.home

import android.content.Context
import android.content.pm.PackageInfo
import com.nnk.appkira.core.logger.Logger
import com.nnk.appkira.core.storage.AppPreferences
import kotlinx.coroutines.flow.toList

interface AppInformationProvider {
    suspend fun getApps(): Result<List<PackageInfo>>
}

private class AppInformationProviderImpl(
    private val context: Context,
    private val appPreferences: AppPreferences,
) : AppInformationProvider {
    override suspend fun getApps(): Result<List<PackageInfo>> =
        try {
            val specialAppsToShow = appPreferences.showSpecialAppsPref.toList()
            Result.success()
        } catch (e: Exception) {
            Logger.e("Error getting apps", e)
            Result.failure(e)
        }
}
