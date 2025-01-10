package com.studytest.kotlin.flow.shared

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn


// stateIn은 Flow를 StateFlow로 전환
// shareIn과 마찬가지로 중단 함수이며, StateFlow는 항상 값을 가져야 합니다.
suspend fun main(): Unit = coroutineScope {
    val flow = flowOf("a", "b", "c")
        .onEach { delay(1000) }
    val stateFlow: StateFlow<String> = flow.stateIn(this)

    stateFlow.collect {
        println(it)
    }
}
