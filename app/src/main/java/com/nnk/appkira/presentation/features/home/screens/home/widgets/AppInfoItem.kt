package com.nnk.appkira.presentation.features.home.screens.home.widgets

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.core.graphics.drawable.toBitmap
import com.nnk.appkira.R
import com.nnk.appkira.core.ext.empty
import com.nnk.appkira.domain.model.AppForceStopMode
import com.nnk.appkira.domain.model.AppInformationModel
import com.nnk.appkira.presentation.designsystem.dimen.AppDimen
import com.nnk.appkira.presentation.designsystem.widgets.AppStopModeButton

@Composable
fun AppInfoItem(appInfo: AppInformationModel) {
    Column {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = AppDimen.Dimen2X, horizontal = AppDimen.Dimen4X),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AppInfoIcon(appInfo.icon, appInfo.name)

            Spacer(modifier = Modifier.width(AppDimen.Dimen4X))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(AppDimen.DimenX),
            ) {
                Text(
                    text = appInfo.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = if (appInfo.isInActive) stringResource(R.string.inactive) else String.empty(),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            Spacer(modifier = Modifier.width(AppDimen.Dimen4X))

            AppStopModeButtons(appInfo.stopMode) {
            }
        }
        HorizontalDivider(
            thickness = AppDimen.Dimen1,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
        )
    }
}

@Composable
private fun AppInfoIcon(
    icon: Drawable?,
    appName: String,
) {
    val bitmap = icon?.toBitmap()?.asImageBitmap()

    val contentDescription = stringResource(R.string.content_desc_app_info_icon, appName)
    val modifier =
        Modifier
            .size(AppDimen.DimenAppInfoIconSize)
            .clip(RoundedCornerShape(AppDimen.Dimen3X))
            .background(MaterialTheme.colorScheme.background)

    if (bitmap != null) {
        Image(
            bitmap = bitmap,
            contentDescription = contentDescription,
            modifier = modifier,
        )
    } else {
        Image(
            painter = painterResource(R.drawable.ic_default_app),
            contentDescription = contentDescription,
            modifier = modifier,
        )
    }
}

@Composable
private fun AppStopModeButtons(
    selectedStopMode: AppForceStopMode,
    onModeChange: (AppForceStopMode) -> Unit,
) {
    var stopMode by remember { mutableStateOf(selectedStopMode) }
    Row(
        modifier = Modifier,
        horizontalArrangement = Arrangement.spacedBy(AppDimen.Dimen2X),
    ) {
        AppStopModeButton(
            mode = AppForceStopMode.Always,
            isSelected = stopMode == AppForceStopMode.Always,
        ) {
            stopMode = it
            onModeChange(it)
        }
        AppStopModeButton(
            mode = AppForceStopMode.WhenInActive,
            isSelected = stopMode == AppForceStopMode.WhenInActive,
        ) {
            stopMode = it
            onModeChange(it)
        }
        AppStopModeButton(
            mode = AppForceStopMode.Never,
            isSelected = stopMode == AppForceStopMode.Never,
        ) {
            stopMode = it
            onModeChange(it)
        }
    }
}
