package com.nnk.appkira.core.system

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings

object AppExternalNavigator {
    fun launchAppSettingsScreen(
        context: Context,
        packageName: String,
    ) {
        val intent =
            Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                data = Uri.fromParts("package", packageName, null)
            }
        context.startActivity(intent)
    }
}
