package com.studytest.kotlin.flow.shared

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch

// shareIn은 Flow를 SharedFlow로 전환하는 역할을 합니다.
// SharingStarted.Eagerly: 즉시 값을 감지, 전송
// SharingStarted.Lazily: 첫 구독자가 나올 때 감지
// SharingStarted.WhileSubscribed: 첫 구독자가 나올 때 감지
//    - 마지막 구독자가 사라지면 플로우 중지
suspend fun main(): Unit = coroutineScope {
    val flow = flowOf("A", "B", "C")
        .onEach { delay(1000) }
    val sharedFlow: SharedFlow<String> = flow.shareIn(
        scope = this, // 플로우의 원소를 모으는 코루틴 시작
        started = SharingStarted.Eagerly
    )

    // 총 3회 수집
    delay(500)
    launch {
        sharedFlow.collect {
            println("#1 $it")
        }
    }
    // 총 2회 수집
    delay(1000)
    launch {
        sharedFlow.collect {
            println("#2 $it")
        }
    }
    // 총 1회 수집
    delay(1000)
    launch {
        sharedFlow.collect {
            println("#3 $it")
        }
    }
}
