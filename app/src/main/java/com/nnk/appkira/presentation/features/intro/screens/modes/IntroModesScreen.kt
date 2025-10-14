package com.nnk.appkira.presentation.features.intro.screens.modes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nnk.appkira.R
import com.nnk.appkira.domain.model.AppForceStopMode
import com.nnk.appkira.presentation.designsystem.color.AppColors
import com.nnk.appkira.presentation.designsystem.dimen.AppDimen
import com.nnk.appkira.presentation.designsystem.dimen.FontSize
import com.nnk.appkira.presentation.designsystem.theme.AppKiraTheme
import com.nnk.appkira.presentation.designsystem.widgets.AppStopModeButton

@Composable
fun IntroModesScreen() {
    Column(
        modifier =
            Modifier
                .fillMaxSize(),
    ) {
        Text(
            modifier = Modifier.padding(horizontal = AppDimen.Dimen4X),
            text = stringResource(R.string.stop_modes),
            style =
                MaterialTheme.typography.titleLarge.copy(
                    fontSize = FontSize.TitleModesScreen,
                ),
            color = MaterialTheme.colorScheme.primary,
        )
        Text(
            modifier = Modifier.padding(horizontal = AppDimen.Dimen4X),
            text = stringResource(R.string.stop_modes_description),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary,
        )

        ModesInfoCard(
            modifier = Modifier.weight(1f),
        )

        Text(
            modifier = Modifier.padding(horizontal = AppDimen.Dimen4X),
            text = stringResource(R.string.empty_string),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary,
        )
    }
}

@Composable
private fun ModesInfoCard(modifier: Modifier) {
    var selectedMode by remember {
        mutableStateOf<AppForceStopMode>(
            AppForceStopMode.Always,
        )
    }

    Column(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(
                    horizontal = AppDimen.Dimen6X,
                    vertical = AppDimen.Dimen2X,
                ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape =
                            RoundedCornerShape(
                                topStart = AppDimen.Dimen4X,
                                topEnd = AppDimen.Dimen4X,
                            ),
                    ).padding(horizontal = AppDimen.Dimen2X)
                    .padding(
                        top = AppDimen.Dimen4X,
                    ),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            AppStopModeButton(
                mode = AppForceStopMode.Always,
                selectedMode = selectedMode,
                onClick = {
                    selectedMode = AppForceStopMode.Always
                },
            )
            AppStopModeButton(
                mode = AppForceStopMode.WhenInActive,
                selectedMode = selectedMode,
                onClick = {
                    selectedMode = AppForceStopMode.WhenInActive
                },
            )
            AppStopModeButton(
                mode = AppForceStopMode.Never,
                selectedMode = selectedMode,
                onClick = {
                    selectedMode = AppForceStopMode.Never
                },
            )
        }
        Text(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .background(
                        color = selectedMode.iconTint,
                        shape =
                            RoundedCornerShape(
                                bottomStart = AppDimen.Dimen4X,
                                bottomEnd = AppDimen.Dimen4X,
                            ),
                    ).padding(all = AppDimen.Dimen4X),
            text = stringResource(R.string.empty_string),
        )
    }
}

@Composable
private fun AppStopModeButton(
    mode: AppForceStopMode,
    selectedMode: AppForceStopMode,
    onClick: (mode: AppForceStopMode) -> Unit,
) {
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AppStopModeButton(
            mode = mode,
            isSelected = selectedMode == mode,
            onClick = onClick,
        )
        if (selectedMode == mode) {
            Icon(
                modifier =
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .rotate(90f),
                imageVector = Icons.Default.PlayArrow,
                contentDescription = stringResource(R.string.content_desc_selected_stop_mode_arrow),
                tint = mode.iconTint,
            )
        }
    }
}

@Preview
@Composable
private fun PreviewModeCard() {
    AppKiraTheme {
        IntroModesScreen()
    }
}
