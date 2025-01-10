package com.studytest.kotlin.flow.lifecycle

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach

// 플로우 빌더가 끝났을 때 (마지막 원소 전송), 호출
suspend fun main(){
    flowOf(1,2)
        .onEach { delay(1000) }
        .onCompletion { println("Completed") }
        .collect{ println(it) }
}
