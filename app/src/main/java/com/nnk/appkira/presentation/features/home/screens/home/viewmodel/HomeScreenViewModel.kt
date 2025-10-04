package com.nnk.appkira.presentation.features.home.screens.home.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel
    @Inject
    constructor() : ViewModel() {
        val list =
            flow {
                delay(4000)
                emit(listOf("A", "B", "C"))
            }
    }
