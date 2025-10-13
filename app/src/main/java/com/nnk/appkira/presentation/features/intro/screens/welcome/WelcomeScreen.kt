package com.nnk.appkira.presentation.features.intro.screens.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.nnk.appkira.R
import com.nnk.appkira.presentation.designsystem.dimen.AppDimen
import com.nnk.appkira.presentation.designsystem.dimen.FontSize

@Composable
fun WelcomeScreen() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
    ) {
        Image(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(AppDimen.Dimen8X),
            painter = painterResource(R.drawable.ic_app_kira),
            contentDescription = stringResource(R.string.content_desc_app_kira_logo),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
        )

        Text(
            modifier = Modifier,
            text = stringResource(R.string.welcome_title),
            style =
                MaterialTheme.typography.titleLarge.copy(
                    fontSize = FontSize.TitleWelcomeScreen,
                ),
            color = MaterialTheme.colorScheme.primary,
        )

        Text(
            modifier = Modifier.padding(start = AppDimen.Dimen8X),
            text = stringResource(R.string.app_name),
            style =
                MaterialTheme.typography.titleLarge.copy(
                    fontSize = FontSize.AppNameWelcomeScreen,
                ),
            color = MaterialTheme.colorScheme.primary,
        )
    }
}
