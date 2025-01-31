package com.android.studytest.datastruct.tree

import com.studytest.datastruct.tree.Node
import com.studytest.datastruct.tree.Tree
import com.studytest.datastruct.tree.inOrder
import com.studytest.datastruct.tree.postOrder
import com.studytest.datastruct.tree.preOrder
import org.junit.Assert.assertEquals
import org.junit.jupiter.api.Test

class TreeTraverseTest {
    private val tree = Tree(
        Node(
            value = 1,
            left = Node(
                value = 2,
                left = Node(value = 4, left = null, right = null),
                right = Node(value = 5, left = null, right = null)
            ),
            right = Node(
                value = 3,
                left = null,
                right = null
            )
        )
    )
    //    1
    //  2  3
    // 4 5

    @Test
    fun `전위_순회_테스트`(){
        val expected = listOf(1,2,4,5,3)
        assertEquals(expected, preOrder(tree))
    }

    @Test
    fun `중위_순회_테스트`() {
        val expected = listOf(4,2,5,1,3)
        assertEquals(expected, inOrder(tree))
    }

    @Test
    fun `후위_순회_테스트`() {
        val expected = listOf(4,5,2,3,1)
        assertEquals(expected, postOrder(tree))
    }
}
