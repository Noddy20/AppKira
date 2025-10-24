package com.nnk.appkira.presentation.features.home.screens.settings.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.nnk.appkira.R
import com.nnk.appkira.data.features.home.SpecialApp
import com.nnk.appkira.presentation.designsystem.dimen.AppDimen
import com.nnk.appkira.presentation.designsystem.theme.AppKiraTheme

@Composable
fun AppsListSettingsDialog(
    appsList: List<SpecialApp>,
    onCancel: () -> Unit,
    onSave: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onCancel,
        title = {
            Text(
                text = stringResource(R.string.apps_list_settings_dialog_title),
                style = MaterialTheme.typography.titleMedium,
            )
        },
        text = {
            Column {
                appsList.forEach { specialApp ->
                    AppsListItem(isChecked = false, type = specialApp) {
                    }
                }
            }
        },
        confirmButton = {
            TextButton(
                onClick = onSave,
                contentPadding = PaddingValues(horizontal = AppDimen.Dimen4X),
            ) {
                Text(
                    text = stringResource(R.string.save),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = onSave,
                contentPadding = PaddingValues(horizontal = AppDimen.Dimen4X),
            ) {
                Text(
                    text = stringResource(R.string.cancel),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
        },
    )
}

@Composable
private fun AppsListItem(
    isChecked: Boolean,
    type: SpecialApp,
    onCheckedChange: (type: SpecialApp) -> Unit,
) {
    val textRes =
        when (type) {
            SpecialApp.LAUNCHER -> R.string.launcher
            SpecialApp.APPKIRA -> R.string.app_name
            SpecialApp.SYSTEM_APPS -> R.string.system_apps
        }

    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(checked = isChecked, onCheckedChange = {
            onCheckedChange(type)
        })
        Text(
            text = stringResource(textRes),
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Preview
@Composable
private fun PreviewAppsListSettingsDialog() {
    AppKiraTheme {
        AppsListSettingsDialog(
            appsList = SpecialApp.entries,
            onCancel = {},
            onSave = {},
        )
    }
}
