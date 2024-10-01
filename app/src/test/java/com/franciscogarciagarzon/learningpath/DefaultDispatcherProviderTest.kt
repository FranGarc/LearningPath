package com.franciscogarciagarzon.learningpath

import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher


class TestDispatcherProvider(
    val testDispatcher: TestDispatcher = StandardTestDispatcher()
): DispatcherProvider{
     override val default
        get() = testDispatcher
     override val io
        get() = testDispatcher
     override val main
        get() = testDispatcher
     override val unconfined
        get() = testDispatcher
}