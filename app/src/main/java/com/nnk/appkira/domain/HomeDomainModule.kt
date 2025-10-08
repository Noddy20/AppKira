package com.nnk.appkira.domain

import com.nnk.appkira.data.features.home.AppInformationProvider
import com.nnk.appkira.domain.usecase.GetInstalledAppsUseCase
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
        GetInstalledAppsUseCase.getInstance(appInformationProvider)
}
