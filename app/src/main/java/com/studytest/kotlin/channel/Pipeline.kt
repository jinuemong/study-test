package com.studytest.kotlin.channel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.coroutineScope

@OptIn(ExperimentalCoroutinesApi::class)
fun CoroutineScope.numbers() : ReceiveChannel<Int> {
    return produce {
        repeat(3){ num ->
            send(num + 1)
        }
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
fun CoroutineScope.square(numbers: ReceiveChannel<Int>) : ReceiveChannel<Int> {
    return produce {
        numbers.consumeEach {
            send( it * it)
        }
    }
}

suspend fun main() = coroutineScope {
    val numbers = numbers()
    val squared = square(numbers)
    squared.consumeEach {
        println(it)
    }
}
