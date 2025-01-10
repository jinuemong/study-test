package com.studytest.kotlin.flow.practice

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.onFailure

// 사용자의 클릭이나 활동 변화를 감지해야 하는 이벤트 플로우
// 감지하는 프로세스는 이벤트를 처리하는 프로세스와 독립적이어야 하므로 채널 플로우 활용 가능
// CallbackFlow를 활용할 경우 콜백 함수를 래핑하는 방식으로 활용 가능

fun <T> flowFrom(api: CallbackBasedApi<T>): Flow<T> = callbackFlow {
    val callback = object : Callback<T> {
        override fun onNextValue(value: T) {
            trySendBlocking(value)
                .onFailure { throwable ->
                    // Downstream이 취소되었거나 실패한 경우, 여기에서 로그를 남길 수 있음
                }
        }
        override fun onApiError(cause: Throwable) {
            cancel(CancellationException("API Error", cause))
        }
        override fun onCompleted() {
            channel.close()
        }
    }
    api.register(callback)

    // 수집자가 취소되거나 'onCompleted'/'onApiError' 호출될 때까지 대기
    awaitClose { api.unregister() }
}

class CallbackBasedApi<T> {
    private var callback: Callback<T>? = null

    // 콜백 등록
    fun register(callback: Callback<T>) {
        this.callback = callback
    }

    // 콜백 해제
    fun unregister() {
        this.callback = null
    }
}

interface Callback<T> {
    fun onNextValue(value: T)
    fun onApiError(cause: Throwable)
    fun onCompleted()
}
