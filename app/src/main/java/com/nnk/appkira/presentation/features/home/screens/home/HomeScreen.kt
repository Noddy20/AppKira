package com.nnk.appkira.presentation.features.home.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nnk.appkira.R
import com.nnk.appkira.core.system.AppExternalNavigator
import com.nnk.appkira.presentation.designsystem.dimen.AppDimen
import com.nnk.appkira.presentation.features.home.screens.home.viewmodel.HomeScreenViewModel
import com.nnk.appkira.presentation.features.home.screens.home.widgets.AppInfoItem

@Composable
fun HomeScreen() {
    val viewModel = hiltViewModel<HomeScreenViewModel>()
    val context = LocalContext.current

    val items = viewModel.list.collectAsStateWithLifecycle(initialValue = emptyList())

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(AppDimen.DimenX),
    ) {
        item { HeaderDivider() }
        item {
            AppListHeader(
                text = stringResource(R.string.running_apps),
            )
        }
        item { HeaderDivider() }
        items(items = items.value) { item ->
            AppInfoItem(
                appInfo = item,
                onClick = {
                    // TODO: Stop
                },
                onLongClick = { appInfo ->
                    AppExternalNavigator.launchAppSettingsScreen(context, appInfo.packageName)
                },
            )
        }

        item {
            AppListHeader(
                text = stringResource(R.string.all_apps),
            )
        }
        item { HeaderDivider() }
        items(items = items.value) { item ->
            AppInfoItem(
                appInfo = item,
                onClick = {
                    // TODO: Stop
                },
                onLongClick = { appInfo ->
                    AppExternalNavigator.launchAppSettingsScreen(context, appInfo.packageName)
                },
            )
        }
    }
}

@Composable
private fun AppListHeader(text: String) {
    Box(
        modifier =
            Modifier
                .wrapContentWidth(align = Alignment.Start)
                .padding(
                    horizontal = AppDimen.Dimen4X,
                    vertical = AppDimen.Dimen2X,
                ).background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(AppDimen.Dimen4X),
                ),
    ) {
        Text(
            text = text,
            modifier =
                Modifier.padding(
                    horizontal = AppDimen.Dimen3X,
                    vertical = AppDimen.Dimen2X,
                ),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onPrimary,
        )
    }
}

@Composable
private fun HeaderDivider() {
    HorizontalDivider(
        thickness = AppDimen.Dimen1,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
    )
}
