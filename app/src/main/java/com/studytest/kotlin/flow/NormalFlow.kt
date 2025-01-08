package com.studytest.kotlin.flow

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// 플로우 빌더는 중단 함수가 아니기 때문에 CoroutineScope가 필요하지 않습니다.
fun userFlow(): Flow<String> = flow {
    repeat(3) {
        delay(1000)
        val ctx = currentCoroutineContext()
        val name = ctx[CoroutineName]?.name
        emit("User$it in $name")
    }
}

suspend fun main() {
    val user = userFlow()
    val coroutineName = CoroutineName("Name")
    withContext(coroutineName) {
        val job = launch {
            // collect는 중단 함수로 스코프 내에서 실행되어야 합니다.
            user.collect {
                println(it)
            }
        }
        // 충분히 기다린 뒤 실행
        delay(2100)
        println("I got enough")
        job.cancel()
    }
}
