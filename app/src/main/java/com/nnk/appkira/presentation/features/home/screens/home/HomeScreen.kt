package com.nnk.appkira.presentation.features.home.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nnk.appkira.presentation.designsystem.dimen.AppDimen
import com.nnk.appkira.presentation.features.home.screens.home.viewmodel.HomeScreenViewModel
import com.nnk.appkira.presentation.features.home.screens.home.widgets.AppInfoItem

@Composable
fun HomeScreen() {
    val viewModel = hiltViewModel<HomeScreenViewModel>()

    val items = viewModel.list.collectAsStateWithLifecycle(initialValue = emptyList())

    Column {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(AppDimen.DimenX),
        ) {
            items(items = items.value) { item ->
                AppInfoItem(item)
            }
        }
    }
}
