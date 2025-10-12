package com.nnk.appkira.core.system

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.core.net.toUri
import com.nnk.appkira.core.logger.Logger

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

    fun launchAppKiraGitRepo(context: Context) {
        try {
            val gitUrl = "https://github.com/Noddy20/AppKira/issues"
            val intent = Intent(Intent.ACTION_VIEW, gitUrl.toUri())
            context.startActivity(intent)
        } catch (e: Exception) {
            Logger.e("Error launching Git repo", e)
        }
    }
}
