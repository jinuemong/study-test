package com.studytest.kotlin.flow.shared

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

suspend fun main(): Unit = coroutineScope {
    // replay : 마지막으로 전송한 값들을 정한 수만큼 저장, default : 0
    // 코루틴이 감지를 시작하면 저장된 값들을 먼저 받음
    val mutableSharedFlow = MutableSharedFlow<String>(
        replay = 2
    )
    mutableSharedFlow.emit("message 1")
    mutableSharedFlow.emit("message 2")
    mutableSharedFlow.emit("message 3")

    //replayCache : 값을 저장한 캐시를 나타냄
    println(mutableSharedFlow.replayCache)

    launch {
        mutableSharedFlow.collect{
            println("received $it")
        }
    }
    //resetReplayCache : 값을 저장한 캐시를 초기화
    mutableSharedFlow.resetReplayCache()
    println(mutableSharedFlow.replayCache)
}
