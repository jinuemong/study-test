package com.studytest.kotlin.channel

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val channel = Channel<Int>()
        launch {
            for (x in 1..10){
                channel.send( x * x)
                // 채널을 닫을 경우 ClosedSendChannelException 발생
//                if (x == 5){
//                    channel.close()
//                }
            }
        }
        launch {
            for (y in channel){
                println(y)
            }
            println("end")
        }
    }
}
