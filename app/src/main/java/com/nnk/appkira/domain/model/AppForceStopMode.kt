package com.nnk.appkira.domain.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.nnk.appkira.R
import com.nnk.appkira.core.logger.Logger
import com.nnk.appkira.presentation.designsystem.color.AppColors

sealed class AppForceStopMode(
    val modeName: String,
    @param:DrawableRes val icon: Int,
    val iconTint: Color,
) {
    object Never : AppForceStopMode(
        modeName = FORCE_STOP_MODE_NEVER,
        icon = R.drawable.ic_shield,
        iconTint = AppColors.Green,
    )

    object Always : AppForceStopMode(
        modeName = FORCE_STOP_MODE_ALWAYS,
        icon = R.drawable.ic_katana,
        iconTint = AppColors.Red,
    )

    object WhenInActive : AppForceStopMode(
        modeName = FORCE_STOP_MODE_WHEN_INACTIVE,
        icon = R.drawable.ic_kunai,
        iconTint = AppColors.Blue,
    )

    companion object Companion {
        private const val FORCE_STOP_MODE_NEVER = "Never"
        private const val FORCE_STOP_MODE_ALWAYS = "Always"
        private const val FORCE_STOP_MODE_WHEN_INACTIVE = "WhenInActive"

        fun getModeByName(name: String): AppForceStopMode =
            when (name) {
                FORCE_STOP_MODE_NEVER -> Never
                FORCE_STOP_MODE_ALWAYS -> Always
                FORCE_STOP_MODE_WHEN_INACTIVE -> WhenInActive
                else -> {
                    Logger.e("Unknown ForceStopMode name: $name, defaulting to Never")
                    Never
                }
            }
    }
}

enum class AutoForceStopDelay(
    val delayInMillis: Long,
) {
    ONE_HOUR(60 * 60 * 1000L),
    TWO_HOURS(2 * 60 * 60 * 1000L),
    FIVE_HOURS(5 * 60 * 60 * 1000L),
    TWELVE_HOURS(12 * 60 * 60 * 1000L),
    ONE_DAY(24 * 60 * 60 * 1000L),
    THREE_DAYS(3 * 24 * 60 * 60 * 1000L),
    ONE_WEEK(7 * 24 * 60 * 60 * 1000L),
}
