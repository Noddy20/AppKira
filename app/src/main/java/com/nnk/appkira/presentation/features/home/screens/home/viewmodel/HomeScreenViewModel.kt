package com.nnk.appkira.presentation.features.home.screens.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nnk.appkira.core.coroutines.DispatchersProvider
import com.nnk.appkira.core.logger.Logger
import com.nnk.appkira.domain.usecase.GetDeviceAppsUseCase
import com.nnk.appkira.presentation.features.home.screens.home.model.HomeScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel
    @Inject
    constructor(
        private val getDeviceAppsUseCase: GetDeviceAppsUseCase,
        private val dispatchersProvider: DispatchersProvider,
    ) : ViewModel() {
        private val _state = MutableStateFlow<HomeScreenUiState>(HomeScreenUiState.Loading)
        val state = _state.asStateFlow()

        fun loadApps() {
            viewModelScope.launch(dispatchersProvider.io) {
                try {
                    _state.value = HomeScreenUiState.Loading
                    val result = getDeviceAppsUseCase()
                    if (result.isSuccess) {
                        val apps =
                            result.getOrThrow().sortedWith(
                                compareBy(
                                    { it.name == it.packageName },
                                    { it.name.lowercase() },
                                ),
                            )
                        val runningApps = apps.filter { it.isRunning }
                        _state.value = HomeScreenUiState.Success(allApps = apps, runningApps = runningApps)
                    } else {
                        _state.value = HomeScreenUiState.Error(error = result.exceptionOrNull())
                    }
                } catch (e: Exception) {
                    Logger.e("Error loading apps", e)
                    _state.value = HomeScreenUiState.Error(error = e)
                }
            }
        }
    }
