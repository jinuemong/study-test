package com.studytest.datastruct.tree

class BinarySearchTree<T: Comparable<T>> {
    private var root : BSTNode<T>? = null

    fun insert(value : T){
        root = insertRec(root,value)
    }

    private fun insertRec(
        node: BSTNode<T>?,
        value : T,
    ): BSTNode<T>{
        node ?: return BSTNode(value)

        if (value < node.value) {
            node.left = insertRec(node.left,value)
        } else if (value > node.value){
            node.right = insertRec(node.right,value)
        }

        return node
    }

    fun isContain(value : T) : Boolean {
        return containsRec(root, value)
    }

    private fun containsRec(
        node: BSTNode<T>?,
        value: T,
    ): Boolean {
        node ?: return false

        return when {
            value < node.value -> containsRec(node.left, value)
            value > node.value -> containsRec(node.right,value)
            else -> true
        }
    }

    fun remove(value: T) {
        root = removeRec(root, value)
    }

    private fun removeRec(node: BSTNode<T>?, value: T): BSTNode<T>? {
        node ?: return null

        when {
            value < node.value -> node.left = removeRec(node.left, value)
            value > node.value -> node.right = removeRec(node.right, value)
            else -> { // 삭제할 값 발견
                // ! 자식 노드가 하나만 있는 경우
                // 왼쪽 값이 없으면, 오른족을 루트로
                if (node.left == null) return node.right
                // 오른쪽 값이 없으면, 왼쪽을 루트로
                if (node.right == null) return node.left

                // ! 자식 노드가 두개 있는 경우
                // 오른쪽 서브 트리에서 가장 작은 값 탐색
                val minValue = findMinNode(node.right!!)
                // 오른쪽 서브 트리의 가장 작은 값 = 삭제 후 루트 노드
                node.value = minValue.value
                // 오른쪽 트리에서 찾은 값 삭제
                node.right = removeRec(node.right, minValue.value)
            }
        }
        return node
    }

    private fun findMinNode(node: BSTNode<T>): BSTNode<T> {
        var current = node
        while (current.left != null) {
            current = current.left!!
        }
        return current
    }
}
