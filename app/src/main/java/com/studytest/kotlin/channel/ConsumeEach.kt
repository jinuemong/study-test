package com.studytest.kotlin.channel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalCoroutinesApi::class)
fun CoroutineScope.produceValue() : ReceiveChannel<Int> {
    return produce {
        for (x in 1.. 10) send( x * x)
    }
}
fun main(){
    runBlocking {
        val produceValue = produceValue()
        produceValue.consumeEach {
            println(it)
        }
        println("end")
    }
}
