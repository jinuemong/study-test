package com.studytest.kotlin.flow.lifecycle

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach

// 중단 함수로, 순서대로 값을 하나씩 받음
suspend fun main() {
    flowOf(1,2,3,4)
        .onEach {
            delay(1000)
        }
        .collect{
            println(it)
        }
}
