package com.studytest.kotlin.flow.internal

import kotlinx.coroutines.delay

// 플로우와 관련된 함수를 직접 생성합니다.

// 원소를 변환하여 새로운 플로우 방출
fun <T, R> Flow<T>.map(
    trasformation: suspend (T) -> R
): Flow<R> = flowBuilder {
    collect {
        emit(trasformation(it))
    }
}

// filter, onEach, onStart 등도 구현이 가능합니다.
fun <T> Flow<T>.filter(
    predicate: suspend (T) -> Boolean
): Flow<T> = flowBuilder {
    collect {
        if (predicate(it)) {
            emit(it)
        }
    }
}

fun <T> Flow<T>.onEach(
    action: suspend (T) -> Unit
): Flow<T> = flowBuilder {
    collect {
        action(it) // 추가 동작 수행
        emit(it)
    }
}
fun <T> Flow<T>.onStart(
    action: suspend () -> Unit
): Flow<T> = flowBuilder {
    action() // 추가 동작을 한 번만 수행
    collect{
        emit(it)
    }
}

// 플로우 생성 함수로 listOf와 비슷하게 동작
fun <T> flowOf(vararg elements: T): Flow<T> = flowBuilder {
    elements.forEach {
        emit(it)
    }
}

suspend fun main() {
    flowOf("A", "B", "C")
        .filter {
            it != "A"
        }
        .onEach {
            println("B or C")
        }
        .onStart {
            println("시작되었습니다")
        }
        .map {
            delay(1000)
            it.plus(it)
        }
        .collect {
            println(it)
        }
}
