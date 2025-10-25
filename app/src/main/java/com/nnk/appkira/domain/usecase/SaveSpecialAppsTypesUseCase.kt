package com.nnk.appkira.domain.usecase

import com.nnk.appkira.core.logger.Logger
import com.nnk.appkira.core.storage.AppPreferences
import com.nnk.appkira.data.features.home.SpecialApp

interface SaveSpecialAppsTypesUseCase {
    suspend operator fun invoke(selectedApps: Set<SpecialApp>)

    companion object {
        fun getInstance(appPreferences: AppPreferences): SaveSpecialAppsTypesUseCase =
            SaveSpecialAppsTypesUseCaseImpl(
                appPreferences = appPreferences,
            )
    }
}

private class SaveSpecialAppsTypesUseCaseImpl(
    private val appPreferences: AppPreferences,
) : SaveSpecialAppsTypesUseCase {
    override suspend fun invoke(selectedApps: Set<SpecialApp>) {
        try {
            val appNames = selectedApps.map { it.name }.toSet()
            appPreferences.setShowSpecialAppsPref(appNames)
        } catch (e: Exception) {
            Logger.e("setShowSpecialAppsPref error!", e)
        }
    }
}
