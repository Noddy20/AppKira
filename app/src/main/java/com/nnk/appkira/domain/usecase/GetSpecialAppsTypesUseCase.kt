package com.nnk.appkira.domain.usecase

import com.nnk.appkira.core.logger.Logger
import com.nnk.appkira.core.storage.AppPreferences
import com.nnk.appkira.data.features.home.SpecialApp

interface GetSpecialAppsTypesUseCase {
    suspend operator fun invoke(): Map<SpecialApp, Boolean>

    companion object {
        fun getInstance(appPreferences: AppPreferences): GetSpecialAppsTypesUseCase =
            GetSpecialAppsTypesUseCaseImpl(
                appPreferences = appPreferences,
            )
    }
}

private class GetSpecialAppsTypesUseCaseImpl(
    private val appPreferences: AppPreferences,
) : GetSpecialAppsTypesUseCase {
    override suspend fun invoke(): Map<SpecialApp, Boolean> =
        try {
            val selectedSpecialApps = appPreferences.getShowSpecialAppsPref()
            val all = SpecialApp.entries
            all.associateWith { selectedSpecialApps.contains(it.name) }
        } catch (e: Exception) {
            Logger.e("getShowSpecialAppsPref error!", e)
            SpecialApp.entries.associateWith { false }
        }
}
