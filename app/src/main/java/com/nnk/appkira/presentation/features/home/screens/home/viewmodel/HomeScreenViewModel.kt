package com.nnk.appkira.presentation.features.home.screens.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nnk.appkira.core.logger.Logger
import com.nnk.appkira.data.features.home.AppInformationProvider
import com.nnk.appkira.domain.usecase.GetInstalledAppsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel
    @Inject
    constructor(
        private val appInformationProvider: AppInformationProvider,
        private val getInstalledAppsUseCase: GetInstalledAppsUseCase,
    ) : ViewModel() {
        val list =
            flow {
                val apps = getInstalledAppsUseCase.invoke().getOrNull().orEmpty()
                Logger.d("Apps loaded : ${apps.size}")
                emit(apps)
            }

        init {
            viewModelScope.launch(Dispatchers.IO) {
                appInformationProvider.getRecentAggregatedUsageStats().forEach {
                    Logger.d("AppUsageStats App: ${it.key} , UsageStats: ${it.value}")
                }
            }
        }
    }
