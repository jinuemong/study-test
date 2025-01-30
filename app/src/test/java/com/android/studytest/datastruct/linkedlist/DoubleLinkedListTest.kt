package com.android.studytest.datastruct.linkedlist

import com.studytest.datastruct.linkedlist.DoubleLinkedList
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertTrue
import org.junit.jupiter.api.Test

class DoubleLinkedListTest {
    private var list: DoubleLinkedList<Int> =  DoubleLinkedList()

    @Test
    fun `양방향_연결리스트_맨_앞_삽입_연산_검증`() {
        // given
        val newValue = 10

        // when
        list.addFirst(newValue)

        // then
        assertEquals(list.get(0), newValue)
        assertEquals(list.size(), 1)
    }

    @Test
    fun `양방향 연결리스트_맨_뒤_삽입_연산_검증`() {
        // given
        val newValue = 10

        // when
        list.addLast(newValue)

        // then
        assertEquals(list.get(list.size() - 1), newValue)
        assertEquals(list.size(), 1)
    }

    @Test
    fun `양방향 연결리스트_특정_인덱스에_값_추가_검증`() {
        // given
        list.addFirst(10)
        list.addFirst(30)
        val addIndex = 1
        val addData = 20

        // when
        list.addAt(addIndex, addData)

        // then
        assertEquals(list.get(addIndex), addData)
    }

    @Test
    fun `양방향 연결리스트_삭제_연산_검증`() {
        // given
        val removeValue = 10
        list.addFirst(removeValue)

        // when
        val result = list.remove(removeValue)

        // then
        assertTrue(result)
        assertEquals(list.size(), 0)
    }

    @Test
    fun `양방향 연결리스트_중간에_값_추가_검증`() {
        // given
        list.addFirst(10)
        list.addFirst(20)
        list.addFirst(30)
        val addIndex = 1
        val addData = 25

        // when
        list.addAt(addIndex, addData)

        // then
        assertEquals(list.get(addIndex), addData)
    }

    @Test
    fun `양방향 연결리스트_빈_리스트에서_삭제_시도_검증`() {
        // given

        // when
        val result = list.remove(10)

        // then
        assertFalse(result)
    }

    @Test
    fun `양방향 연결리스트_인덱스_범위_초과_검증`() {
        // given
        list.addFirst(10)

        // when
        val outOfBoundsResult = list.get(1)

        // then
        assertNull(outOfBoundsResult)
    }
}
