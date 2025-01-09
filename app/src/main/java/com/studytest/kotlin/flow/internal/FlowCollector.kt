package com.studytest.kotlin.flow.internal

// 이전의 코드는 함수를 파라미터로 전달합니다.
// 복잡한 함수형을 간결하게 만들기 위하여 함수형 인터페이스로 추상화합니다.
private suspend fun <T> before(){
    val f: suspend ((T) -> Unit) -> Unit = { emit ->
        emit("flow 방출" as T)
    }
}

// 함수형 인터페이스를 화용하여 람다식으로 정의합니다.
fun interface FlowCollector<T> {
    suspend fun emit(value: T)
}

// 람다식 내부에 FlowColletor 타입의 리시버를 생성했습니다.
private suspend fun <T> after(){
    val f: suspend FlowCollector<T>.() -> Unit = {
        emit("flow 방출" as T)
    }
}
