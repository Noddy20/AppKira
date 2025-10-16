package com.nnk.appkira.presentation.features.intro.screens.permission

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.nnk.appkira.R
import com.nnk.appkira.presentation.designsystem.dimen.AppDimen
import com.nnk.appkira.presentation.designsystem.dimen.FontSize
import com.nnk.appkira.presentation.designsystem.theme.AppKiraTheme

@Composable
fun PermissionScreen() {
    Column(
        modifier =
            Modifier
                .fillMaxSize(),
    ) {
        Text(
            modifier = Modifier.padding(horizontal = AppDimen.Dimen4X),
            text = stringResource(R.string.permission),
            style =
                MaterialTheme.typography.titleLarge.copy(
                    fontSize = FontSize.TitleModesScreen,
                ),
            color = MaterialTheme.colorScheme.primary,
        )
        Text(
            modifier = Modifier.padding(horizontal = AppDimen.Dimen4X),
            text = stringResource(R.string.permission_description),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary,
        )

        HorizontalDivider(
            modifier = Modifier.padding(top = AppDimen.Dimen10X),
            thickness = AppDimen.Dimen1,
            color = MaterialTheme.colorScheme.onSurface,
        )

        PermissionButton(
            text = stringResource(R.string.permission_usage_access),
            isChecked = false,
        ) {
        }
    }
}

@Composable
private fun PermissionButton(
    text: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = AppDimen.Dimen4X,
                    vertical = AppDimen.Dimen4X,
                ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
        )
        Switch(
            modifier = Modifier,
            checked = isChecked,
            onCheckedChange = onCheckedChange,
        )
    }
    HorizontalDivider(
        thickness = AppDimen.Dimen1,
        color = MaterialTheme.colorScheme.onSurface,
    )
}

@Preview
@Composable
fun PreviewPermissionScreen() {
    AppKiraTheme {
        PermissionScreen()
    }
}
