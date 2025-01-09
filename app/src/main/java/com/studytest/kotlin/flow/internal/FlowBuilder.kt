package com.studytest.kotlin.flow.internal

// 복잡한 플로우 생성 로직을 내부로 캡슐화합니다.
fun <T> flowBuilder(
    builder: suspend FlowCollector<T>.() -> Unit
) = object : Flow<T> {
    override suspend fun collect(collector: FlowCollector<T>) {
        collector.builder()
    }
}

// 플로우와 똑같이 동작합니다.
suspend fun main() {
    val f: Flow<String> = flowBuilder {
        emit("flow 방출 1")
        emit("flow 방출 2")
    }

    f.collect {
        println("수집1 : $it")
    }
    f.collect {
        println("수집2 : $it")
    }
}
