package com.android.studytest.kotlin.flow.test

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.delayEach
import kotlinx.coroutines.launch
import kotlin.time.Duration

// 짧은 시간 동안만 유지되는 List를 통해 끝나지않는 flow 테스트
suspend fun <T> Flow<T>.toListDuring(
    duration: Duration
): List<T> = coroutineScope {
    val result = mutableListOf<T>()
    val job = launch {
        this@toListDuring.collect(result::add)
    }
    delay(duration)
    job.cancel()
    return@coroutineScope result
}
