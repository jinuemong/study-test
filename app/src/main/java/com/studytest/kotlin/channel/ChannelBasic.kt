package com.studytest.kotlin.channel

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val channel = Channel<Int>()
        launch { // 코루틴 스코프 1
            for (x in 1..10){
                channel.send( x * x)
            }
        }
        launch { // 코루틴 스코프 2
            repeat(10){
                println(channel.receive())
            }
        }
    }
}
