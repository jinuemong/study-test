package com.android.studytest.datastruct.hashcrash

import com.studytest.datastruct.hashcrash.QuadraticProbingHash
import org.junit.Assert.assertNotSame
import org.junit.jupiter.api.Test

class QuadraticProbingHashTest {
    private val hash = QuadraticProbingHash<Int, String>()

    @Test
    fun `이차_조사법_적용_시_해시_충돌이_발생하지_않는다`() {
        //given
        hash.put(10, "A")

        //when
        hash.put(20, "B")

        //then
        assertNotSame(hash.get(10), hash.get(20))
    }
}
