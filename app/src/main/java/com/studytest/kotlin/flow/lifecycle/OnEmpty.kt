package com.studytest.kotlin.flow.lifecycle

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEmpty

// 예기치 않은 이벤트가 발생하면 플로우는 값을 내보내기 전에 완료됩니다
// onEmpty는 기본값을 내보내기 위한 목적으로 사용될 수 있습니다.
suspend fun main() = coroutineScope {
    // 값을 내보내지 않는 플로우
    flow<List<Int>> { delay(1000) }
        // 아무 값을 내보내지 않았으므로, onEmpty 호출
        .onEmpty { emit(emptyList()) }
        .collect { println(it) }
}
