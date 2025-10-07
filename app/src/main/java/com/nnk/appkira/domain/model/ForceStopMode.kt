package com.nnk.appkira.domain.model

sealed class ForceStopMode(
    val modeName: String,
) {
    object Never : ForceStopMode(FORCE_STOP_MODE_NEVER)

    object Always : ForceStopMode(FORCE_STOP_MODE_ALWAYS)

    data class WhenInActive(
        val inActiveForMillis: Long,
    ) : ForceStopMode(FORCE_STOP_MODE_WHEN_INACTIVE)
}

const val FORCE_STOP_MODE_NEVER = "Never"
const val FORCE_STOP_MODE_ALWAYS = "Always"
const val FORCE_STOP_MODE_WHEN_INACTIVE = "WhenInActive"
