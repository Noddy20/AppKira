package com.nnk.appkira.presentation.features.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.nnk.appkira.presentation.designsystem.theme.AppKiraTheme
import com.nnk.appkira.presentation.features.home.screens.HomeContainerScreen
import com.nnk.appkira.presentation.features.intro.IntroActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppKiraTheme {
                if (true) {
                    startActivity(IntroActivity.getIntent(this))
                    finish()
                } else {
                    HomeContainerScreen()
                }
            }
        }
    }

    companion object {
        fun getIntent(context: Context): Intent = Intent(context, HomeActivity::class.java)
    }
}
