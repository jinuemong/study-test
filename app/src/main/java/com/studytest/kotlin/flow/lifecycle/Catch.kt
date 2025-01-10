package com.studytest.kotlin.flow.lifecycle

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach

class MyError : Throwable("error")
val flow = flow{
    emit(1)
    emit(2)
    throw MyError()
}

// 플로우를 만들거나 처리하는 도중에 발생하는 에러를 제어할 수 있습니다.
suspend fun main(){
    flow.onEach { println(it) } // onEach는 예외에 잡히지 않음
        .catch { println(it) } // 예외 출력
        .collect{ println(it) }
}
