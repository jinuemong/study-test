package com.studytest.kotlin.flow.practice

import kotlinx.coroutines.flow.asFlow

// asFlow 함수를 이용해 즉시 사용 가능한 원소들의 플로우를 만들 수 있습니다.
suspend fun main(){
    sequenceOf(1,2,3,4,5) // sequenceOf
        .toSet() //setOf
        .toList() // listOf
        .asFlow()
        .collect {
            println(it)
        }
}
