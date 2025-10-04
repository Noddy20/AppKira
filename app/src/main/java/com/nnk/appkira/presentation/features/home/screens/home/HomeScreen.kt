package com.nnk.appkira.presentation.features.home.screens.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nnk.appkira.presentation.features.home.screens.home.viewmodel.HomeScreenViewModel

@Composable
fun HomeScreen() {
    val viewModel = hiltViewModel<HomeScreenViewModel>()
    val list = viewModel.list.collectAsStateWithLifecycle(emptyList())
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        items(items = list.value) { item ->
            Text(text = "Item : $item")
        }
    }
}
