package com.nnk.appkira.core.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.nnk.appkira.core.storage.AppPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {
    @Provides
    @Singleton
    fun providesAppPreferences(
        @ApplicationContext context: Context,
    ): AppPreferences = AppPreferences.getInstance(context.appPreferencesDataStore)
}

private val Context.appPreferencesDataStore by preferencesDataStore("app_kira_prefs")
