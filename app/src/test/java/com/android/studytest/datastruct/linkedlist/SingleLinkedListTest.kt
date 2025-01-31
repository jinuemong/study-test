package com.android.studytest.datastruct.linkedlist

import com.studytest.datastruct.linkedlist.SingleLinkedList
import org.junit.Assert.assertEquals
import org.junit.jupiter.api.Test

class SingleLinkedListTest {
    private var list: SingleLinkedList<Int> = SingleLinkedList()

    @Test
    fun `연결리스트_맨_앞_삽입_연산_검증`(){
        //given
        val newValue = 10

        //when
        list.addFirst(newValue)

        //then
        assertEquals(list.get(0),newValue)
    }

    @Test
    fun `연결리스트_맨_뒤_삽입_연산_검증`() {
        //given
        val newValue = 10

        //when
        list.addLast(newValue)

        //then
        assertEquals(list.get(list.size()-1),newValue)
    }

    @Test
    fun `연결리스트_삭제_연산_검증`() {
        //given
        val removeValue = 10
        list.addLast(removeValue)

        //when
        val expected =  list.remove(removeValue)

        //then
        assertEquals(removeValue,expected)
    }

    @Test
    fun `연결리스트_특정_인덱스에_값_추가_검증`() {
        //given
        list.addFirst(10)
        list.addFirst(30)
        val addIndex = 1
        val addData = 20

        //when
        list.addAt(addIndex,addData)

        //then
        assertEquals(list.get(addIndex),addData)
    }
}
