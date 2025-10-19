package com.nnk.appkira.domain.usecase

import android.content.Context
import com.nnk.appkira.core.logger.Logger
import eu.chainfire.libsuperuser.Shell

interface StopAppsWithRootUseCase {
    suspend operator fun invoke(): Boolean
}

private class StopAppsWithRootUseCaseImpl(
    private val context: Context,
    private val appsPackageNames: Set<String>,
    private val turnOffScreenAfterStop: Boolean,
) : StopAppsWithRootUseCase {
    override suspend fun invoke(): Boolean =
        try {
            val shell = Shell.Pool.SU.get()
            appsPackageNames.forEach {
                shell.run("am set-inactive $it true")
                if (it == context.packageName) {
                    if (turnOffScreenAfterStop) shell.run("input keyevent 26")
                }
                shell.run("am force-stop $it")
                shell.run("am kill $it")
            }
            if (turnOffScreenAfterStop) shell.run("input keyevent 26")
            true
        } catch (e: Shell.ShellDiedException) {
            Logger.e("Root access issue! Maybe no root access available.", e)
            false
        }
}
