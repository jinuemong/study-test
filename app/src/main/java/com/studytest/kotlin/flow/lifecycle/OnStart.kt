package com.studytest.kotlin.flow.lifecycle

import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onStart

// 플로우가 시작되는 경우 호출되는 리스너
// onStart에서도 emit 가능
suspend fun main(){
    flowOf(1,2)
        .onStart { println("Start") }
        .collect{ println(it) }
    flowOf(1,2)
        .onStart { emit(0) }
        .collect{ println(it) }
}
