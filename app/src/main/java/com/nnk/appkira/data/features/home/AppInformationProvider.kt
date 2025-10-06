package com.nnk.appkira.data.features.home

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import com.nnk.appkira.BuildConfig
import com.nnk.appkira.core.logger.Logger
import com.nnk.appkira.core.storage.AppPreferences

interface AppInformationProvider {
    suspend fun getInstalledApps(): Result<List<PackageInfo>>
}

private class AppInformationProviderImpl(
    private val context: Context,
    private val appPreferences: AppPreferences,
) : AppInformationProvider {
    @SuppressLint("QueryPermissionsNeeded")
    override suspend fun getInstalledApps(): Result<List<PackageInfo>> =
        try {
            val specialAppsToShow = appPreferences.getShowSpecialAppsPref()
            val launcherPackageName = getLauncherAppPackageName()

            val showLauncherApp = specialAppsToShow.contains(SpecialApp.LAUNCHER.name)
            val showAppKira = specialAppsToShow.contains(SpecialApp.APPKIRA.name)
            val showSystemApps = specialAppsToShow.contains(SpecialApp.SYSTEM_APPS.name)

            fun shouldShowSpecialApps(info: PackageInfo): Boolean {
                if (!showLauncherApp && info.packageName == launcherPackageName) return false
                if (!showAppKira && info.packageName == BuildConfig.APPLICATION_ID) return false
                if (!showSystemApps && (info.applicationInfo?.isSystemApp() ?: false)) return false
                return true
            }

            val result =
                context.packageManager
                    .getInstalledPackages(PackageManager.GET_META_DATA)
                    .filter(::shouldShowSpecialApps)
            Result.success(result)
        } catch (e: Exception) {
            Logger.e("Error getting apps", e)
            Result.failure(e)
        }

    private fun getLauncherAppPackageName(): String? {
        val packageManager = context.applicationContext.packageManager

        val intent = Intent("android.intent.action.MAIN")
        intent.addCategory("android.intent.category.HOME")

        return packageManager
            .resolveActivity(
                intent,
                PackageManager.MATCH_DEFAULT_ONLY,
            )?.activityInfo
            ?.packageName
    }

    private fun ApplicationInfo.isSystemApp(): Boolean = flags.isFlagSet(ApplicationInfo.FLAG_SYSTEM)

    private fun Int.isFlagSet(value: Int): Boolean = (this and value) == value
}

enum class SpecialApp {
    LAUNCHER,
    APPKIRA,
    SYSTEM_APPS,
}
