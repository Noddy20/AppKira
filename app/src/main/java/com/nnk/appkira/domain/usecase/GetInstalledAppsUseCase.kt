package com.nnk.appkira.domain.usecase

import com.nnk.appkira.core.logger.Logger
import com.nnk.appkira.data.features.home.AppInformationProvider
import com.nnk.appkira.domain.model.AppInformationModel

interface GetInstalledAppsUseCase {
    suspend operator fun invoke(): Result<List<AppInformationModel>>

    companion object {
        fun getInstance(appInformationProvider: AppInformationProvider): GetInstalledAppsUseCase =
            GetInstalledAppsUseCaseImpl(
                appInformationProvider = appInformationProvider,
            )
    }
}

private class GetInstalledAppsUseCaseImpl(
    private val appInformationProvider: AppInformationProvider,
) : GetInstalledAppsUseCase {
    override suspend fun invoke(): Result<List<AppInformationModel>> =
        try {
            val apps = appInformationProvider.getInstalledApps().getOrThrow()
            val usageStats = appInformationProvider.getRecentAggregatedUsageStats()
            Logger.d("Fetched ${apps.size} installed apps")
            val appList =
                apps.map { packageInfo ->
                    val usageStat = usageStats[packageInfo.packageName]
                    val appInfo = appInformationProvider.getAppInfo(packageInfo.packageName)
                    val appLabel = appInfo?.let { appInformationProvider.getAppLabel(it) }
                    val appIcon = appInfo?.let { appInformationProvider.getAppIcon(it) }
                    val isAppRunning = appInfo?.let { appInformationProvider.isAppRunning(it) } ?: false
                    AppInformationModel(
                        packageName = packageInfo.packageName,
                        name = appLabel ?: packageInfo.packageName,
                        icon = appIcon,
                        isRunning = isAppRunning,
                    )
                }
            Result.success(appList)
        } catch (e: Exception) {
            Logger.e("Error fetching installed apps", e)
            Result.failure(e)
        }
}
