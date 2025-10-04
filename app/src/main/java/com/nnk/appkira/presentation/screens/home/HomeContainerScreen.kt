package com.nnk.appkira.presentation.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.nnk.appkira.R
import com.nnk.appkira.presentation.designsystem.color.AppColors
import com.nnk.appkira.presentation.designsystem.dimen.AppDimen
import com.nnk.appkira.presentation.designsystem.theme.AppKiraTheme
import com.nnk.appkira.presentation.screens.dashboard.DashboardScreen

@Composable
fun HomeContainerScreen() {
    var selectedTab by remember { mutableStateOf(BottomTab.Home) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
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

@Composable
fun AppBottomBar(
    selectedTab: BottomTab,
    onTabSelected: (BottomTab) -> Unit,
) {
    NavigationBar(
        modifier =
            Modifier
                .fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = AppDimen.DimenX,
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    modifier = Modifier.size(AppDimen.Dimen6X, AppDimen.Dimen6X),
                    imageVector = ImageVector.vectorResource(R.drawable.ic_android),
                    contentDescription = "Apps",
                )
            },
            selected = selectedTab == BottomTab.Home,
            onClick = { onTabSelected(BottomTab.Home) },
            colors =
                NavigationBarItemDefaults.colors(
                    selectedIconColor = AppColors.Green,
                    selectedTextColor = AppColors.Green,
                ),
        )
        NavigationBarItem(
            icon = {
                Icon(
                    modifier =
                        Modifier
                            .size(AppDimen.Dimen6X, AppDimen.Dimen6X),
                    imageVector = ImageVector.vectorResource(R.drawable.ic_utilities),
                    contentDescription = "About",
                )
            },
            selected = selectedTab == BottomTab.Dashboard,
            onClick = { onTabSelected(BottomTab.Dashboard) },
            colors =
                NavigationBarItemDefaults.colors(
                    selectedIconColor = AppColors.Green,
                    selectedTextColor = AppColors.Green,
                ),
        )
    }
}

// Enum for tab selection
enum class BottomTab {
    Home,
    Dashboard,
}

@Preview
@Composable
fun HomeScreenPreview() {
    AppKiraTheme {
        HomeContainerScreen()
    }
}
