package com.studytest.kotlin.flow.lifecycle

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

// collect는 플로우가 완료될 때까지 코루틴을 중단하는 중단 연산입니다.
// launch 빌더로 collect를 래핑하면 플로우를 다른 코루틴에서 처리할 수 있습니다.
// launchIn을 사용하면 유일한 인자로 스코프를 받아 collect를 새로운 코루틴에서 시작할 수 있습니다.
// 별도의 코루틴에서 플로우를 시작합니다.
suspend fun main(): Unit = coroutineScope {
    flowOf("user1","user2")
        .onStart{ println("User Info")}
        .onEach { println(it) }
        .launchIn(this)
}
