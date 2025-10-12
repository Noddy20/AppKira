package com.nnk.appkira.presentation.features.home.screens.home.model

import com.nnk.appkira.domain.model.AppInformationModel

sealed interface HomeScreenUiState {
    object Loading : HomeScreenUiState

    data class Success(
        val allApps: List<AppInformationModel>,
        val runningApps: List<AppInformationModel>,
    ) : HomeScreenUiState

    data class Error(
        val error: Throwable?,
    ) : HomeScreenUiState
}
