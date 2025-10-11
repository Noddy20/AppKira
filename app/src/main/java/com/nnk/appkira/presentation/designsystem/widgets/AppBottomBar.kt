package com.nnk.appkira.presentation.designsystem.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.nnk.appkira.R
import com.nnk.appkira.presentation.designsystem.dimen.AppDimen

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
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                ),
        )
        NavigationBarItem(
            icon = {
                Icon(
                    modifier =
                        Modifier
                            .size(AppDimen.Dimen6X, AppDimen.Dimen6X),
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Settings",
                )
            },
            selected = selectedTab == BottomTab.Dashboard,
            onClick = { onTabSelected(BottomTab.Dashboard) },
            colors =
                NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                ),
        )
    }
}

// Enum for tab selection
enum class BottomTab {
    Home,
    Dashboard,
}
