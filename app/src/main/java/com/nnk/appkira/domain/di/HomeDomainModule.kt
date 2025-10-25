package com.nnk.appkira.domain.di

import com.nnk.appkira.core.storage.AppPreferences
import com.nnk.appkira.data.features.home.AppInformationProvider
import com.nnk.appkira.domain.usecase.GetDeviceAppsUseCase
import com.nnk.appkira.domain.usecase.GetSpecialAppsTypesUseCase
import com.nnk.appkira.domain.usecase.SaveSpecialAppsTypesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object HomeDomainModule {
    @Provides
    @ActivityRetainedScoped
    fun providesGetInstalledAppsUseCase(appInformationProvider: AppInformationProvider) =
        GetDeviceAppsUseCase.Companion.getInstance(appInformationProvider)

    @Provides
    @ActivityRetainedScoped
    fun providesGetSpecialAppsTypesUseCase(appPreferences: AppPreferences) =
        GetSpecialAppsTypesUseCase.getInstance(
            appPreferences = appPreferences,
        )

    @Provides
    @ActivityRetainedScoped
    fun providesSaveSpecialAppsTypesUseCase(appPreferences: AppPreferences) =
        SaveSpecialAppsTypesUseCase.getInstance(
            appPreferences = appPreferences,
        )
}
