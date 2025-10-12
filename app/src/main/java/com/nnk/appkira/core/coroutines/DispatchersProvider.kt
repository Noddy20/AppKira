package com.nnk.appkira.core.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface DispatchersProvider {
    val main: CoroutineDispatcher
    val default: CoroutineDispatcher
    val io: CoroutineDispatcher
    val unconfined: CoroutineDispatcher

    companion object {
        fun getInstance(): DispatchersProvider = DispatchersProviderImpl()
    }
}

private class DispatchersProviderImpl : DispatchersProvider {
    override val io: CoroutineDispatcher
        get() = Dispatchers.IO

    override val main: CoroutineDispatcher
        get() = Dispatchers.Main

    override val default: CoroutineDispatcher
        get() = Dispatchers.Default

    override val unconfined: CoroutineDispatcher
        get() = Dispatchers.Unconfined
}
