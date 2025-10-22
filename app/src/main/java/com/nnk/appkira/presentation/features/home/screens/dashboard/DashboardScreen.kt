package com.nnk.appkira.presentation.features.home.screens.dashboard

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.nnk.appkira.R
import com.nnk.appkira.presentation.designsystem.dimen.AppDimen
import com.nnk.appkira.presentation.designsystem.theme.AppKiraTheme

@Composable
fun DashboardScreen() {
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
            text = stringResource(R.string.apps_list),
        )
        ListItem(
            icon = Icons.Default.DateRange,
            text = stringResource(R.string.inactive_stop_interval),
        )
        ListItem(
            icon = Icons.Default.PlayArrow,
            text = stringResource(R.string.launch_intro_screen),
        )
        ListItem(
            icon = Icons.Default.Edit,
            text = stringResource(R.string.report_issue),
        )
    }
}

@Composable
private fun ListItem(
    icon: ImageVector,
    text: String,
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = AppDimen.Dimen4X,
                    vertical = AppDimen.Dimen5X,
                ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(AppDimen.Dimen4X),
    ) {
        Icon(
            imageVector = icon,
            contentDescription = stringResource(R.string.content_desc_info_list_icon, text),
            tint = MaterialTheme.colorScheme.onBackground,
        )
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
        )
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
        DashboardScreen()
    }
}
