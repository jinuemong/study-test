package com.android.studytest.datastruct

import junit.framework.TestCase.assertEquals
import org.junit.jupiter.api.Test
import java.util.Stack

class StackTest {
    val stack = Stack<Int>()

    @Test
    fun `stack에_데이터_삽입_시_가장_뒤에_데이터가_추가된다`(){
        //given
        stack.push(10)
        val actual = 20

        //when
        stack.push(actual)
        val expected = stack.last()

        //then
        assertEquals(actual,expected)
    }

    @Test
    fun `stack에서_데이터가_삭제_되면_가장_최신의_데이터가_삭제된다`(){
        //given
        stack.push(10)
        val actual = 20

        ///when
        stack.push(actual)
        val expected = stack.pop()

        //then
        assertEquals(actual,expected)
    }
}
