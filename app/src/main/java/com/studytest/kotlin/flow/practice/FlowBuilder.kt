package com.studytest.kotlin.flow.practice

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

public fun <T> flowOf(vararg elements: T): Flow<T> = flow {
    elements.forEach {
        emit(it)
    }
}
