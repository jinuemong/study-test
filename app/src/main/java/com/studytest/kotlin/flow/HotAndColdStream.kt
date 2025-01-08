package com.studytest.kotlin.flow

// 두 스트림의 차이를 이해합니다.

fun main() {
    // 핫 스트림과 콜드 스트림
    // 핫 스트림
    val l = buildList {
        repeat(3) {
            add("User$it")
            println("Added User in list")
        }
    }

    val l2 = l.map {
        println("Processing in list")
        "Processed $it"
    }

    // 콜드 스트림
    val s = sequence {
        repeat(3) {
            // yield:시퀀스나 제너레이터에서 값을 반환하고, 함수의 실행을 중단한 후 다시 재개
            yield("User$it")
            println("Added User in sequence")
        }
    }

    val s2 = s.map {
        println("Processing in sequence")
    }
}
