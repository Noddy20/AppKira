package com.nnk.appkira.domain.model

import android.graphics.drawable.Drawable

data class AppInformationModel(
    val packageName: String,
    val name: String,
    val icon: Drawable?,
    val isRunning: Boolean,
    val isUsedRecently: Boolean,
    val isInActive: Boolean,
)
