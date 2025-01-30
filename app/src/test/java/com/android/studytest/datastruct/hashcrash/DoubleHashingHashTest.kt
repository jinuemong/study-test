package com.android.studytest.datastruct.hashcrash

import com.studytest.datastruct.hashcrash.DoubleHashingHash
import junit.framework.TestCase.assertNotSame
import org.junit.jupiter.api.Test

class DoubleHashingHashTest {
    private val hash = DoubleHashingHash<Int,String>()

    @Test
    fun `이중_해싱_적용_시_해시_충돌이_발생하지_않는다`(){
        //given
        hash.put(10,"A")

        //when
        hash.put(20,"B")

        //then
        assertNotSame(hash.get(10), hash.get(20))
    }
}
