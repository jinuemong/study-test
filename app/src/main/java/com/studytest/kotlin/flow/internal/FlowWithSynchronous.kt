package com.studytest.kotlin.flow.internal

import kotlinx.coroutines.delay

suspend fun main() {
    flowOf("A","B","C")
        .onEach { delay(1000) }
        .collect{ println(it) }
}
