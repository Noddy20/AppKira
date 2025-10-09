package com.nnk.appkira.domain.model

import com.nnk.appkira.core.logger.Logger

sealed class AppForceStopMode(
    val modeName: String,
) {
    object Never : AppForceStopMode(FORCE_STOP_MODE_NEVER)

    object Always : AppForceStopMode(FORCE_STOP_MODE_ALWAYS)

    object WhenInActive : AppForceStopMode(FORCE_STOP_MODE_WHEN_INACTIVE)

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
