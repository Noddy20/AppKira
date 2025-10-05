package com.nnk.appkira.presentation.features.home.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nnk.appkira.presentation.designsystem.dimen.AppDimen
import com.nnk.appkira.presentation.features.home.screens.home.viewmodel.HomeScreenViewModel

@Composable
fun HomeScreen() {
    val viewModel = hiltViewModel<HomeScreenViewModel>()
    val list = viewModel.list.collectAsStateWithLifecycle(emptyList())
    val context = LocalContext.current
    val items = viewModel.getInstalledApps(context)
    Column {
        Text("Total Apps: ${items.size}")
        Spacer(modifier = Modifier.height(AppDimen.Dimen2).fillMaxWidth())
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {
            items(items = items) { item ->
                Text(text = "Item : $item")
            }
        }
    }
}
