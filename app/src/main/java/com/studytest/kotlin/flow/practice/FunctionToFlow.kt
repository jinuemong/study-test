package com.studytest.kotlin.flow.practice

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow

// 일반 함수를 플로우로 변환할 수 있습니다.
// 함수 참조값이 필요하며, 코틀린에서는 ::를 활용합니다.
suspend fun getUserName(): String {
    delay(1000)
    return "UserName"
}

suspend fun main() {
    // 일반 함수 플로우로 활용
    ::getUserName
        .asFlow()
        .collect { println(it) }

    // 중단 함수를 플로우로 변환할 수 있습니다.
    // 이때 중단 함수의 결과가 플로우의 유일한 값이 됩니다.
    val function = suspend {
        // 중단 함수를 람다식으로 생성합니다.
        delay(1000)
        "UserName" // 결과 반환
    }

    function
        .asFlow()
        .collect { println(it) }
}
