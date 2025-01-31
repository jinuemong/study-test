package com.studytest.datastruct.tree

fun <T> preOrder(
    tree : Tree<T>,
): List<T> {
    val result= mutableListOf<T>()

    fun traverse(node: Node<T>?) {
        node ?: return

        result.add(node.value)
        traverse(node.left)
        traverse(node.right)
    }

    traverse(tree.root)
    return  result.toList()
}
