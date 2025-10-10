package com.nnk.appkira.presentation.designsystem.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.nnk.appkira.domain.model.AppForceStopMode
import com.nnk.appkira.presentation.designsystem.color.AppColors
import com.nnk.appkira.presentation.designsystem.dimen.AppDimen

@Composable
fun AppStopModeButton(
    mode: AppForceStopMode,
    isSelected: Boolean,
    size: Dp = AppDimen.DimenAppStopModeIconSize,
    onClick: (mode: AppForceStopMode) -> Unit,
) {
    val color =
        if (isSelected.not()) {
            AppColors.DarkLight
        } else {
            mode.iconTint
        }

    Column {
        Image(
            imageVector = ImageVector.vectorResource(mode.icon),
            contentDescription = "Stop Mode Button",
            modifier =
                Modifier
                    .size(size)
                    .padding(AppDimen.Dimen6)
                    .clickable(
                        onClick = { onClick(mode) },
                    ),
            colorFilter = ColorFilter.tint(color),
        )
    }
}

@Preview
@Composable
private fun PreviewAppStopModeButton() {
    AppStopModeButton(
        AppForceStopMode.WhenInActive,
        isSelected = true,
        onClick = {},
    )
}
