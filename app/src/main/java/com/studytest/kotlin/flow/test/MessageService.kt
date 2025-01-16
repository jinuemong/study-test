package com.studytest.kotlin.flow.test

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.shareIn

data class Message(
    val fromUserId : String,
    val message: String,
)

// 끝나지 않는 플로우 테스트
// 상태플로우, 공유 플로우를 테스트하는 방법을 학습합니다.

class MessageService(
    messageSource : Flow<Message>,
    scope: CoroutineScope,
) {
    private val source = messageSource
        .shareIn(
            scope = scope,
            started = SharingStarted.WhileSubscribed()
        )

    fun observeMessage(fromUserId: String) = source
        .filter { it.fromUserId == fromUserId }
}
