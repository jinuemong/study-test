package com.studytest.kotlin.flow

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

// 채널은 코루틴 스코프 내부에서 실행되어야 합니다.
@OptIn(ExperimentalCoroutinesApi::class)
private fun CoroutineScope.makeChannel() = produce {
    println("Channel make")
    for (i in 1..2){
        delay(1000)
        send(i)
    }
}

// 플로우는 생성시 코루틴을 필요로 하지 않습니다.
// flow 빌더는 빌더를 호출한 최종 연산의 스코프에서 실행됩니다.
private fun makeflow() = flow {
    println("Flow make")
    for(i in 1..2){
        delay(1000)
        emit(i)
    }
}

suspend fun main() = coroutineScope {
    val channel = makeChannel()
    val flow = makeflow()

    delay(1000)
    println("Start channel")
    // 바로 값을 생성 합니다. (Start 이전에 수행)
    channel.consumeEach {
        println("channel consume : $it")
    }
    println("ReStart channel")
    // 첫 소비자가 수신했기 때문에, 채널이 비어있음
    channel.consumeEach {
        println("channel reConsume : $it")
    }

    println("-----------------------")
    delay(1000)
    println("Start flow")
    // 콜드 데이테 소스로, 값이 필요할 때만 생성
    flow.collect {
        println("flow consume : $it")
    }
    println("ReStart flow")
    // 처음부터 다시 데이터를 처리하기 때문에, 또다시 수집합니다.
    flow.collect {
        println("flow reConsume : $it")
    }
}
