package com.nnk.appkira.presentation.features.home.screens.home

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.core.graphics.drawable.toBitmap
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nnk.appkira.R
import com.nnk.appkira.domain.model.AppForceStopMode
import com.nnk.appkira.domain.model.AppInformationModel
import com.nnk.appkira.presentation.designsystem.color.AppColors
import com.nnk.appkira.presentation.designsystem.dimen.AppDimen
import com.nnk.appkira.presentation.designsystem.widgets.AppStopModeButton
import com.nnk.appkira.presentation.features.home.screens.home.viewmodel.HomeScreenViewModel

@Composable
fun HomeScreen() {
    val viewModel = hiltViewModel<HomeScreenViewModel>()

    val items = viewModel.list.collectAsStateWithLifecycle(initialValue = emptyList())

    Column {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(AppDimen.DimenX),
        ) {
            items(items = items.value) { item ->
                AppInfoItem(item)
            }
        }
    }
}

@Composable
private fun AppInfoItem(appInfo: AppInformationModel) {
    val context = LocalContext.current
    Column {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = AppDimen.Dimen2X, horizontal = AppDimen.Dimen4X),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val bitmap =
                remember(appInfo.icon) {
                    val icon = appInfo.icon ?: context.getDrawable(R.drawable.ic_default_app)
                    icon?.toBitmap(width = 96, height = 96)?.asImageBitmap()
                }

            Image(
                bitmap = bitmap!!,
                contentDescription = "App Icon: ${appInfo.name}",
                modifier =
                    Modifier
                        .size(AppDimen.DimenAppInfoIconSize)
                        .clip(RoundedCornerShape(AppDimen.Dimen3X))
                        .background(MaterialTheme.colorScheme.background),
            )

            Spacer(modifier = Modifier.width(AppDimen.Dimen4X))

            Column(
                modifier = Modifier.weight(1f),
            ) {
                // 2. App Name
                Text(
                    text = appInfo.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    maxLines = 1,
                )
                Text(
                    text = appInfo.packageName,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                )
            }

            Spacer(modifier = Modifier.width(AppDimen.Dimen4X))

            var stopMode by remember { mutableStateOf(appInfo.stopMode) }
            // 3. Running Status Pill
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.spacedBy(AppDimen.Dimen2X),
            ) {
                AppStopModeButton(
                    mode = AppForceStopMode.Always,
                    isSelected = stopMode == AppForceStopMode.Always,
                ) {
                    stopMode = it
                }
                AppStopModeButton(
                    mode = AppForceStopMode.WhenInActive,
                    isSelected = stopMode == AppForceStopMode.WhenInActive,
                ) {
                    stopMode = it
                }
                AppStopModeButton(
                    mode = AppForceStopMode.Never,
                    isSelected = stopMode == AppForceStopMode.Never,
                ) {
                    stopMode = it
                }
            }
        }
        HorizontalDivider(
            thickness = AppDimen.Dimen1,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
        )
    }
}
