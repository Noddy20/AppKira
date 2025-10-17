package com.nnk.appkira.presentation.features.intro

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.nnk.appkira.R
import com.nnk.appkira.core.ext.toast
import com.nnk.appkira.core.system.PermissionManager
import com.nnk.appkira.presentation.designsystem.theme.AppKiraTheme
import com.nnk.appkira.presentation.features.home.HomeActivity
import com.nnk.appkira.presentation.features.intro.screens.IntroContainerScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class IntroActivity : ComponentActivity() {
    @Inject
    lateinit var permissionManager: PermissionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppKiraTheme(darkTheme = true) {
                IntroContainerScreen(permissionManager) {
                    if (permissionManager.isUsageStatePermissionGranted()) {
                        startActivity(HomeActivity.getIntent(this))
                        finish()
                    } else {
                        toast(getString(R.string.grant_required_permissions))
                    }
                }
            }
        }
    }

    companion object {
        fun getIntent(context: Context): Intent = Intent(context, IntroActivity::class.java)
    }
}
