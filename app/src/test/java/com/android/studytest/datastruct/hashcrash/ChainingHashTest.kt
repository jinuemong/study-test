package com.android.studytest.datastruct.hashcrash

import com.studytest.datastruct.hashcrash.ChainingHash
import org.junit.Assert.assertNotSame
import org.junit.jupiter.api.Test

class ChainingHashTest {
    private val hashTable = ChainingHash<Int, String>()

    @Test
    fun `채이닝_알고리즘_적용_시_해시_충돌이_발생하지_않는다`() {
        //given
        hashTable.put(10, "A")

        //when
        // 같은 해시 인덱스 추가
        hashTable.put(20, "B")

        //then
        assertNotSame(hashTable.get(10), hashTable.get(20))
    }
}
