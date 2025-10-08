package com.nnk.appkira.domain.model

sealed class ForceStopMode(
    val modeName: String,
) {
    object Never : ForceStopMode(FORCE_STOP_MODE_NEVER)

    object Always : ForceStopMode(FORCE_STOP_MODE_ALWAYS)

    data class WhenInActive(
        val inActiveForMillis: Long = AutoForceStopDelay.ONE_WEEK.delayInMillis,
    ) : ForceStopMode(FORCE_STOP_MODE_WHEN_INACTIVE)
}

const val FORCE_STOP_MODE_NEVER = "Never"
const val FORCE_STOP_MODE_ALWAYS = "Always"
const val FORCE_STOP_MODE_WHEN_INACTIVE = "WhenInActive"

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
