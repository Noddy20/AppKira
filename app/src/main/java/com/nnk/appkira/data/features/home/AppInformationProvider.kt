package com.nnk.appkira.data.features.home

import android.annotation.SuppressLint
import android.app.usage.UsageStats
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import com.nnk.appkira.BuildConfig
import com.nnk.appkira.core.logger.Logger
import com.nnk.appkira.core.storage.AppPreferences
import java.util.concurrent.TimeUnit

interface AppInformationProvider {
    suspend fun getInstalledApps(): Result<List<PackageInfo>>

    suspend fun getAppInfo(packageName: String): ApplicationInfo?

    suspend fun getAppLabel(applicationInfo: ApplicationInfo): String?

    suspend fun getAppIcon(applicationInfo: ApplicationInfo): Drawable?

    suspend fun getAppForceStopMode(packageName: String): Result<String>

    suspend fun isAppRunning(applicationInfo: ApplicationInfo): Boolean

    suspend fun getRecentAggregatedUsageStats(): Map<String, UsageStats>

    companion object {
        fun getInstance(
            context: Context,
            appPreferences: AppPreferences,
        ): AppInformationProvider =
            AppInformationProviderImpl(
                context = context,
                appPreferences = appPreferences,
            )
    }
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

            val apps =
                context.packageManager
                    .getInstalledPackages(PackageManager.GET_META_DATA)
            val result = apps.filter(::shouldShowSpecialApps)
            Logger.d("getInstalledApps: fetched ${apps.size} apps | filtered ${result.size} apps")
            Result.success(result)
        } catch (e: Exception) {
            Logger.e("Error getting apps", e)
            Result.failure(e)
        }

    override suspend fun getAppInfo(packageName: String): ApplicationInfo? =
        try {
            context.packageManager.getApplicationInfo(packageName, 0)
        } catch (e: Exception) {
            Logger.e("Error getting app info for $packageName", e)
            null
        }

    override suspend fun getAppLabel(applicationInfo: ApplicationInfo): String? =
        applicationInfo.loadLabel(context.packageManager).toString()

    override suspend fun getAppIcon(applicationInfo: ApplicationInfo): Drawable? = applicationInfo.loadIcon(context.packageManager)

    override suspend fun getAppForceStopMode(packageName: String): Result<String> {
        return try {
            Result.success(appPreferences.getAppForceStopModePref(packageName))
        } catch (e: Exception) {
            Logger.e("Error getting force stop mode for $packageName", e)
            return Result.failure(e)
        }
    }

    override suspend fun isAppRunning(applicationInfo: ApplicationInfo): Boolean =
        !applicationInfo.flags.isFlagSet(ApplicationInfo.FLAG_STOPPED)

    override suspend fun getRecentAggregatedUsageStats(): Map<String, UsageStats> =
        try {
            val usageStatsManager = context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager

            val delay = appPreferences.getAutoForceStopDelayInMillis()
            val now = System.currentTimeMillis()
            val startDate = now - delay
            Logger.d("getRecentAggregatedUsageStats $now | $startDate ${TimeUnit.MILLISECONDS.toDays(now - startDate)}")

            usageStatsManager.queryAndAggregateUsageStats(startDate, now).orEmpty()
        } catch (e: Exception) {
            Logger.e("Error getting recent aggregated usage stats", e)
            emptyMap()
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
