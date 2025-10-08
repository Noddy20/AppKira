package com.nnk.appkira.presentation.features.home.screens.dashboard

import android.app.AppOpsManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.nnk.appkira.core.system.PermissionManager

@Composable
fun DashboardScreen() {
    val context = LocalContext.current

    Column {
        Text("Dashboard Screen")

        Button(onClick = {
            val pm = PermissionManager.getInstance(context)
            if (pm.isUsageStatePermissionGranted().not()) {
                pm.launchUsageAccessSettings()
            }
        }) {
            Text("Request Usage Stats Permission")
        }
    }
}
