package com.nnk.appkira.presentation.features.intro.screens.permission

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
    }
}

@Preview
@Composable
fun PreviewPermissionScreen() {
    AppKiraTheme {
        PermissionScreen()
    }
}
