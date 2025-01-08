package com.studytest.kotlin.channel

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.selects.select

fun main() = runBlocking {
    val channel1 = Channel<String>()
    val channel2 = Channel<String>()

    launch {
        delay(1000)
        channel1.send("channel1")
    }

    launch {
        delay(500)
        channel2.send("channel2")
    }

    // select 블록을 사용하여 채널에서 값을 받음
    select<Unit> {
        // 채널 1 select
        channel1.onReceive { message ->
            println("Received from channel1: $message")
        }
        // 채널 2 select
        channel2.onReceive { message ->
            println("Received from channel2: $message")
        }
    }
}
