package com.studytest.kotlin.flow.lifecycle

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext

// 플로우 연산과 람다식은 모두 중단 함수입니다.
// 중단 함수는 컨텍스트를 필요하며(구조화된 동시성), 부모와 관계를 유지합니다.
// 플로우의 함수들은 collect가 호출되는 곳의 컨텍스트를 얻습니다.

fun userFlow(): Flow<String> = flow {
    repeat(2){
        val ctx=  currentCoroutineContext()
        val name = ctx[CoroutineName]?.name
        emit("User$it in $name")
    }
}

suspend fun main() {
    val users = userFlow()
    withContext(CoroutineName("Name1")){
        users.collect{
            println(it)
        }
    }
    withContext(CoroutineName("Name2")){
        users.collect {
            println(it)
        }
    }
    println("-------------------")
    // flowOn을 활용할 수 있습니다.
    // 이는 컨텍스트를 변경할 때 유용합니다.
    // flowOn은 플로우에서 윗부분에 있는 함수에서만 작동한다는 특징이 있습니다.
    withContext(CoroutineName("Name1")){
        users
            .flowOn(CoroutineName("Name2"))
            .onEach { println(it) }
            .flowOn(CoroutineName("Name3"))
            .onEach { println(it) }
            .collect {
                println("collect $it")
            }
    }
}
