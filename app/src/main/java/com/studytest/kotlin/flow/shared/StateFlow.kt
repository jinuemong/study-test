package com.studytest.kotlin.flow.shared

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

// StateFlow는 공유 플로우의 개념을 확장시킨 것,
// replay = 1인 공유 플로우와 비슷하게 작동
// 상태플로우는 value 프로퍼티로 접근 가능한 값 하나를 항상 가짐
// 라이브데이터를 대체하기 위해 지원
// 코루틴을 지원하며, 초기값을 가지기 때문에 null 안전
suspend fun main(): Unit = coroutineScope {
    val state = MutableStateFlow("A")
    launch {
        state.collect{
            println(it)
        }
    }

    delay(100)
    // 상태 변화 시 수집하므로, B는 2번 출력
    state.value = "B"
    launch {
        state.collect{
            println(it)
        }
    }
}
