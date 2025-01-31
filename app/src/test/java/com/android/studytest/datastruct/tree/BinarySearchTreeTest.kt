package com.android.studytest.datastruct.tree

import com.studytest.datastruct.tree.BinarySearchTree
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BinarySearchTreeTest {
    private val bst = BinarySearchTree<Int>()

    @BeforeEach
    fun setUp() {
        bst.insert(50)
        bst.insert(30)
        bst.insert(70)
        bst.insert(90)
    }

    @Test
    fun `값_탐색_검증`() {
        assert(bst.isContain(30))
        assert(bst.isContain(50))
        assert(bst.isContain(70))
        assert(bst.isContain(90))
    }

    @Test
    fun `값_추가_검증`() {
        //given
        val actual = 20
        assert(!bst.isContain(actual))

        //when
        bst.insert(actual)

        //then
        assert(bst.isContain(actual))
    }

    @Test
    fun `값_삭제_검증`() {
        //given
        val target = 30
        assert(bst.isContain(target))

        //when
        bst.remove(target)

        //then
        assert(!bst.isContain(target))
    }

}

