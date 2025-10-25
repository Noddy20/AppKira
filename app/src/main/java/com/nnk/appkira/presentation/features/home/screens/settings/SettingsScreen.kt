package com.nnk.appkira.presentation.features.home.screens.settings

import androidx.activity.ComponentActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.nnk.appkira.BuildConfig
import com.nnk.appkira.R
import com.nnk.appkira.core.system.AppExternalNavigator
import com.nnk.appkira.data.features.home.SpecialApp
import com.nnk.appkira.presentation.designsystem.dimen.AppDimen
import com.nnk.appkira.presentation.designsystem.theme.AppKiraTheme
import com.nnk.appkira.presentation.features.home.screens.settings.viewmodel.SettingsViewModel
import com.nnk.appkira.presentation.features.home.screens.settings.widgets.AppsListSettingsDialog
import com.nnk.appkira.presentation.features.intro.IntroActivity
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen() {
    val context = LocalContext.current
    val scope = LocalLifecycleOwner.current.lifecycleScope

    val viewModel = hiltViewModel<SettingsViewModel>()

    var isAppsListDialogShown by remember { mutableStateOf(false) }
    var appsList by remember { mutableStateOf(emptyMap<SpecialApp, Boolean>()) }

    LaunchedEffect(isAppsListDialogShown) {
        if (isAppsListDialogShown) {
            scope.launch {
                appsList = viewModel.getSpecialApps()
            }
        } else {
            appsList = emptyMap()
        }
    }

    if (isAppsListDialogShown && appsList.isNotEmpty()) {
        AppsListSettingsDialog(
            appsList = appsList,
            onCancel = {
                isAppsListDialogShown = false
            },
            onSave = {
                viewModel.saveSpecialApps(it)
                isAppsListDialogShown = false
            },
        )
    }

    Column(
        modifier =
            Modifier
                .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(AppDimen.DimenX),
    ) {
        HorizontalDivider(
            modifier = Modifier,
            thickness = AppDimen.Dimen1,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
        )
        ListItem(
            icon = Icons.AutoMirrored.Default.List,
            title = stringResource(R.string.apps_list),
            subTitle = stringResource(R.string.apps_list_subtitle),
        ) {
            if (isAppsListDialogShown.not()) {
                isAppsListDialogShown = true
            }
        }
        ListItem(
            icon = Icons.Default.DateRange,
            title = stringResource(R.string.inactive_stop_interval),
            subTitle =
                stringResource(
                    R.string.inactive_stop_interval_subtitle,
                    3,
                    stringResource(R.string.days),
                ),
            isEnabled = false,
        )
        ListItem(
            icon = Icons.Default.PlayArrow,
            title = stringResource(R.string.launch_intro_screen),
        ) {
            context.startActivity(IntroActivity.getIntent(context))
            (context as ComponentActivity).finish()
        }
        ListItem(
            icon = Icons.Default.Edit,
            title = stringResource(R.string.report_issue),
            subTitle = stringResource(R.string.report_issue_subtitle),
        ) {
            AppExternalNavigator.launchAppKiraGitRepoIssues(context)
        }

        Text(
            modifier =
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = AppDimen.Dimen8X),
            text =
                stringResource(
                    R.string.app_version_info,
                    stringResource(R.string.app_name),
                    BuildConfig.VERSION_NAME,
                ),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onBackground,
        )
    }
}

@Composable
private fun ListItem(
    icon: ImageVector,
    title: String,
    subTitle: String? = null,
    isEnabled: Boolean = true,
    onClick: () -> Unit = { },
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = AppDimen.Dimen4X,
                    vertical = AppDimen.Dimen4X,
                ).clickable(
                    enabled = isEnabled,
                    onClick = onClick,
                ).alpha(
                    if (isEnabled) 1.0f else 0.6f,
                ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(AppDimen.Dimen4X),
    ) {
        Icon(
            imageVector = icon,
            contentDescription = stringResource(R.string.content_desc_info_list_icon, title),
            tint = MaterialTheme.colorScheme.onBackground,
        )
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )
            if (subTitle != null) {
                Text(
                    modifier = Modifier.padding(top = AppDimen.DimenX),
                    text = subTitle,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                )
            }
        }
    }
    HorizontalDivider(
        modifier = Modifier,
        thickness = AppDimen.Dimen1,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
    )
}

@Preview
@Composable
private fun PreviewDashboardScreen() {
    AppKiraTheme {
        SettingsScreen()
    }
}
