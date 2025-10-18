package com.nnk.appkira.presentation.features.intro

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.nnk.appkira.R
import com.nnk.appkira.core.coroutines.DispatchersProvider
import com.nnk.appkira.core.ext.toast
import com.nnk.appkira.core.storage.AppPreferences
import com.nnk.appkira.core.system.PermissionManager
import com.nnk.appkira.presentation.designsystem.theme.AppKiraTheme
import com.nnk.appkira.presentation.features.home.HomeActivity
import com.nnk.appkira.presentation.features.intro.screens.IntroContainerScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class IntroActivity : ComponentActivity() {
    @Inject
    lateinit var permissionManager: PermissionManager

    @Inject
    lateinit var appPreferences: AppPreferences

    @Inject
    lateinit var dispatchersProvider: DispatchersProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppKiraTheme {
                IntroContainerScreen(permissionManager) {
                    if (permissionManager.isUsageStatePermissionGranted()) {
                        saveIntroShownStatus()
                        startActivity(HomeActivity.getIntent(this))
                        finish()
                    } else {
                        toast(getString(R.string.grant_required_permissions))
                    }
                }
            }
        }
    }

    private fun saveIntroShownStatus() {
        MainScope().launch(dispatchersProvider.io) {
            appPreferences.setAppIntroShownStatus()
        }
    }

    companion object {
        fun getIntent(context: Context): Intent = Intent(context, IntroActivity::class.java)
    }
}
