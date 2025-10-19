package com.nnk.appkira.domain.usecase

import com.nnk.appkira.core.logger.Logger
import eu.chainfire.libsuperuser.Shell

interface IsDeviceRootedUseCase {
    suspend operator fun invoke(): Boolean

    companion object {
        fun getInstance(): IsDeviceRootedUseCase = IsDeviceRootedUseCaseImpl()
    }
}

private class IsDeviceRootedUseCaseImpl : IsDeviceRootedUseCase {
    override suspend fun invoke(): Boolean =
        try {
            Shell.SU.available()
        } catch (e: Shell.ShellDiedException) {
            Logger.e("Root check failed", e)
            false
        }
}
