package com.nnk.appkira.presentation.features.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.nnk.appkira.core.storage.AppPreferences
import com.nnk.appkira.presentation.designsystem.theme.AppKiraTheme
import com.nnk.appkira.presentation.features.home.screens.HomeContainerScreen
import com.nnk.appkira.presentation.features.intro.IntroActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    @Inject
    lateinit var appPreferences: AppPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AppKiraTheme {
                val lifecycleOwner = LocalLifecycleOwner.current
                var isAppIntroShown by remember { mutableStateOf(true) }

                LaunchedEffect(null) {
                    lifecycleOwner.lifecycleScope.launch {
                        isAppIntroShown = appPreferences.getAppIntroShownStatus()
                    }
                }

                when {
                    isAppIntroShown.not() -> {
                        startActivity(IntroActivity.getIntent(this))
                        finish()
                    }
                    else -> {
                        HomeContainerScreen()
                    }
                }
            }
        }
    }

    companion object {
        fun getIntent(context: Context): Intent = Intent(context, HomeActivity::class.java)
    }
}
