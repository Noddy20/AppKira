package com.nnk.appkira.presentation.designsystem.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nnk.appkira.presentation.designsystem.dimen.AppDimen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: String,
    showBackIcon: Boolean,
    onBackPressed: () -> Unit = {},
) {
    TopAppBar(
        title = {
            Text(
                modifier = Modifier.padding(start = if (showBackIcon) AppDimen.DimenX else AppDimen.DimenZero),
                text = title,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleLarge,
            )
        },
        navigationIcon = {
            if (showBackIcon) {
                Icon(
                    modifier =
                        Modifier
                            .size(AppDimen.Dimen8X)
                            .clickable {
                                onBackPressed()
                            },
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    tint = MaterialTheme.colorScheme.onBackground,
                    contentDescription = null,
                )
            }
        },
        actions = {
            /*
            IconButton(onClick = {
             }) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "Settings"
                )
            }
             */
        },
        colors =
            TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background,
                titleContentColor = MaterialTheme.colorScheme.onBackground,
                actionIconContentColor = MaterialTheme.colorScheme.onBackground,
                navigationIconContentColor = MaterialTheme.colorScheme.onBackground,
            ),
    )
}
