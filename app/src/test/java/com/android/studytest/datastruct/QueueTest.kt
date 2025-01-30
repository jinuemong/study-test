package com.android.studytest.datastruct

import junit.framework.TestCase.assertEquals
import org.junit.jupiter.api.Test
import java.util.PriorityQueue

class QueueTest {
    val queue = ArrayDeque<Int>()
    val pQueue = PriorityQueue<Int>()

    @Test
    fun `큐에_데이터를_추가하면_맨_앞에_데이터가_추가된다`(){
        //given
        queue.addFirst(10)
        val actual = 20

        //when
        queue.addFirst(actual)
        val expected = queue.first()

        //then
        assertEquals(expected,actual)
    }

    @Test
    fun `큐에서_데이터를_삭제하면_가장_먼저_추가한_데이터가_삭제된다`(){
        //given
        val actual = 10
        queue.addFirst(actual)
        queue.addFirst(20)

        //when
        val expected = queue.removeLast()

        //then
        assertEquals(actual,expected)
    }

    @Test
    fun `우선순위_큐에서_데이터를_삭제하면_가장_작은_데이터가_삭제된다`(){
        //given
        val actual = 10
        pQueue.add(20)
        pQueue.add(actual)
        pQueue.add(30)

        //when
        val expected = pQueue.poll()

        //then
        assertEquals(actual,expected)
    }
}
