package com.studytest.kotlin.flow.internal

// 기존의 FlowCollector를 다음과 같이 사용할 수 있습니다.
// 람다식 전달은 가독성이 떨어지므로, 인터페이스를 구현한 객체를 생성합니다.
private suspend fun <T> before() {
    val f: suspend FlowCollector<T>.() -> Unit = {
        emit("flow 방출" as T)
    }
    f {
        println(it) // flow 방출
    }
}

interface Flow<T> {
    suspend fun collect(collector: FlowCollector<T>)
}

private suspend fun <T> after() {
    val flow: Flow<T> = object : Flow<T> {
        override suspend fun collect(collector: FlowCollector<T>) {
            collector.emit("flow 방출" as T)
        }
    }

    flow.collect {
        println(it)
    }
}
