package com.android.studytest.datastruct

import org.assertj.core.api.Assertions
import org.junit.Assert.assertEquals
import org.junit.jupiter.api.Test

class ArrayPerformanceTest {
    private val n = 1_000_000
    private val arr = IntArray(n) { it }

    @Test
    fun `삭제에_대한_최악의_시간복잡도_검증`(){
        val list = arr.toMutableList()
        var operationCount = 0

        list[0] = -1 // 임의 값

        for (i in 0..<list.lastIndex){ //리스트 이동
            list[i] = list[i+1]
            operationCount++
        }

        list.removeLast() // 삭제 연산 수행
        operationCount++

        assertEquals(operationCount,n)
    }

    @Test
    fun `삽입에_대한_최악의_시간복잡도_검증`() {
        val list = arr.toMutableList()
        var operationCount = 0

        val temp = list.last()

        for (i in 0..<list.lastIndex) { // O(N-1)번 이동 발생
            list[i+1] = list[i]
            operationCount++
        }

        list[0] = -1 // 삽입 연산
        list.add(temp)
        operationCount++

        assertEquals(operationCount, n)
    }

    @Test
    fun `탐색에_대한_최악의_시간복잡도_검증`(){

    }
}
