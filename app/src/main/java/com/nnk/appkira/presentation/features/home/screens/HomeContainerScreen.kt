package com.nnk.appkira.presentation.features.home.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.nnk.appkira.presentation.designsystem.theme.AppKiraTheme
import com.nnk.appkira.presentation.designsystem.widgets.AppBottomBar
import com.nnk.appkira.presentation.designsystem.widgets.AppTopBar
import com.nnk.appkira.presentation.designsystem.widgets.BottomTab
import com.nnk.appkira.presentation.features.home.screens.dashboard.DashboardScreen
import com.nnk.appkira.presentation.features.home.screens.home.HomeScreen

@Composable
fun HomeContainerScreen() {
    var selectedTab by remember { mutableStateOf(BottomTab.Home) }

    Scaffold(
        topBar = {
            AppTopBar("AppKira", showBackIcon = false)
        },
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            AppBottomBar(selectedTab = selectedTab) {
                selectedTab = it
            }
        },
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (selectedTab) {
                BottomTab.Home -> {
                    HomeScreen()
                }

                BottomTab.Dashboard -> {
                    DashboardScreen()
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    AppKiraTheme {
        HomeContainerScreen()
    }
}
