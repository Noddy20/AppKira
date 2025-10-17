package com.nnk.appkira.core.system

import android.app.AppOpsManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.core.content.ContextCompat

interface PermissionManager {
    fun isUsageStatePermissionGranted(): Boolean

    fun launchUsageAccessSettings()

    companion object {
        fun getInstance(context: Context): PermissionManager = PermissionManagerImpl(context)
    }
}

private class PermissionManagerImpl(
    private val context: Context,
) : PermissionManager {
    override fun isUsageStatePermissionGranted(): Boolean {
        val appOps = context.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
        val mode =
            appOps.checkOpNoThrow(
                AppOpsManager.OPSTR_GET_USAGE_STATS,
                android.os.Process.myUid(),
                context.packageName,
            )

        return if (mode == AppOpsManager.MODE_DEFAULT) {
            ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.PACKAGE_USAGE_STATS,
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            mode == AppOpsManager.MODE_ALLOWED
        }
    }

    override fun launchUsageAccessSettings() {
        val intent = Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS)
        intent.data = Uri.fromParts("package", context.packageName, null)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }
}
