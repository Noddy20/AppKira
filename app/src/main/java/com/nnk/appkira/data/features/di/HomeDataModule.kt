package com.nnk.appkira.data.features.di

import android.content.Context
import com.nnk.appkira.core.storage.AppPreferences
import com.nnk.appkira.data.features.home.AppInformationProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object HomeDataModule {
    @Provides
    @ActivityRetainedScoped
    fun providesAppInformationProvider(
        @ApplicationContext context: Context,
        appPreferences: AppPreferences,
    ): AppInformationProvider =
        AppInformationProvider.getInstance(
            context = context,
            appPreferences = appPreferences,
        )
}
