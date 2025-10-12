package com.nnk.appkira.presentation.features.home.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nnk.appkira.R
import com.nnk.appkira.core.ext.copyContentToClipboard
import com.nnk.appkira.core.system.AppExternalNavigator
import com.nnk.appkira.domain.model.AppInformationModel
import com.nnk.appkira.presentation.designsystem.dimen.AppDimen
import com.nnk.appkira.presentation.features.home.screens.home.model.HomeScreenUiState
import com.nnk.appkira.presentation.features.home.screens.home.viewmodel.HomeScreenViewModel
import com.nnk.appkira.presentation.features.home.screens.home.widgets.AppInfoItem

@Composable
fun HomeScreen() {
    val viewModel = hiltViewModel<HomeScreenViewModel>()

    val state = viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(state) {
        viewModel.loadApps()
    }

    when (val uiState = state.value) {
        is HomeScreenUiState.Loading -> {
            LoadingUi()
        }
        is HomeScreenUiState.Error -> {
            ErrorUi(uiState.error) {
                viewModel.loadApps()
            }
        }
        is HomeScreenUiState.Success -> {
            AppList(
                allApps = uiState.allApps,
                runningApps = uiState.runningApps,
            ) {
                viewModel.loadApps()
            }
        }
    }
}

@Composable
private fun LoadingUi() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(
            modifier = Modifier,
            color = MaterialTheme.colorScheme.primary,
            strokeWidth = AppDimen.DimenX,
        )
    }
}

@Composable
private fun ErrorUi(
    error: Throwable?,
    onRetry: () -> Unit,
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column {
            Text(
                modifier =
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(horizontal = AppDimen.Dimen4X),
                textAlign = TextAlign.Center,
                text = stringResource(R.string.error_generic_message),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Button(
                modifier =
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = AppDimen.Dimen4X),
                onClick = onRetry,
            ) {
                Text(
                    text = stringResource(R.string.try_again),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            }
            TextButton(
                modifier =
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = AppDimen.Dimen4X),
                onClick = {
                    context.copyContentToClipboard(
                        label = context.getString(R.string.error_log_copied),
                        content = error?.stackTraceToString() ?: "No error available",
                    )
                    AppExternalNavigator.launchAppKiraGitRepo(context)
                },
            ) {
                Text(
                    text = stringResource(R.string.report_error),
                    style =
                        MaterialTheme.typography.bodyMedium.copy(
                            textDecoration = TextDecoration.Underline,
                        ),
                    color = MaterialTheme.colorScheme.error,
                )
            }
        }
    }
}

@Composable
private fun AppList(
    allApps: List<AppInformationModel>,
    runningApps: List<AppInformationModel>,
    isRefreshing: Boolean = false,
    onRefresh: () -> Unit,
) {
    val context = LocalContext.current
    var isRefreshing by remember { mutableStateOf(isRefreshing) }
    val pullToRefreshState = rememberPullToRefreshState()

    LazyColumn(
        modifier =
            Modifier
                .fillMaxSize()
                .pullToRefresh(
                    isRefreshing = isRefreshing,
                    state = pullToRefreshState,
                    onRefresh = {
                        isRefreshing = true
                        onRefresh()
                    },
                ),
        verticalArrangement = Arrangement.spacedBy(AppDimen.DimenX),
    ) {
        item { HeaderDivider() }
        item {
            AppListHeader(
                text = stringResource(R.string.running_apps, runningApps.size),
            )
        }
        item { HeaderDivider() }
        items(items = runningApps) { item ->
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
                text = stringResource(R.string.all_apps, allApps.size),
            )
        }
        item { HeaderDivider() }
        items(items = allApps) { item ->
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
