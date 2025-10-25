package com.nnk.appkira.presentation.features.home.screens.settings.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nnk.appkira.core.coroutines.DispatchersProvider
import com.nnk.appkira.data.features.home.SpecialApp
import com.nnk.appkira.domain.usecase.GetSpecialAppsTypesUseCase
import com.nnk.appkira.domain.usecase.SaveSpecialAppsTypesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel
    @Inject
    constructor(
        private val getSpecialAppsTypes: GetSpecialAppsTypesUseCase,
        private val saveSpecialAppsTypes: SaveSpecialAppsTypesUseCase,
        private val dispatchersProvider: DispatchersProvider,
    ) : ViewModel() {
        private var _specialApps: Map<SpecialApp, Boolean> = emptyMap()

        suspend fun getSpecialApps(): Map<SpecialApp, Boolean> {
            if (_specialApps.isEmpty()) {
                _specialApps =
                    withContext(dispatchersProvider.io) {
                        getSpecialAppsTypes()
                    }
            }
            return _specialApps
        }

        fun saveSpecialApps(apps: Map<SpecialApp, Boolean>) {
            viewModelScope.launch(dispatchersProvider.io) {
                _specialApps = apps
                val selectedApps = apps.filter { it.value }.keys
                saveSpecialAppsTypes(selectedApps)
            }
        }
    }
