package com.nnk.appkira.presentation.features.home.screens.home.viewmodel

import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel
    @Inject
    constructor() : ViewModel() {
        val list =
            flow {
                delay(4000)
                emit(listOf("A", "B", "C"))
            }

        fun getInstalledApps(context: Context): List<AppInfo> {
            val packageManager = context.packageManager
            val packages = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
            return packages
                .mapNotNull { app ->
                    // Filter out system apps if needed
                    val isSystemApp = (app.flags and ApplicationInfo.FLAG_SYSTEM) != 0
                    // Uncomment the next line to exclude system apps:
                    // if (isSystemApp) return@mapNotNull null
                    AppInfo(
                        appName = packageManager.getApplicationLabel(app).toString(),
                        packageName = app.packageName,
                        icon = packageManager.getApplicationIcon(app),
                    )
                }.sortedBy { it.appName.lowercase() }
        }

        fun getLaunchableApps(context: Context): List<AppInfo> {
            val pm = context.packageManager
            val intent = Intent(Intent.ACTION_MAIN, null)
            intent.addCategory(Intent.CATEGORY_LAUNCHER)
            val resolveInfos = pm.queryIntentActivities(intent, 0)
            return resolveInfos
                .map {
                    AppInfo(
                        appName = it.loadLabel(pm).toString(),
                        packageName = it.activityInfo.packageName,
                        icon = it.loadIcon(pm),
                    )
                }.sortedBy { it.appName.lowercase() }
        }

        // AppInfo data class for easy handling
        data class AppInfo(
            val appName: String,
            val packageName: String,
            val icon: Drawable,
        )
    }
