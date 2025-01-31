package com.studytest.datastruct.tree

fun<T> inOrder(
    tree: Tree<T>
): List<T> {
    val result = mutableListOf<T>()

    fun traverse(node : Node<T>?){
        node ?: return

        traverse(node.left)
        result.add(node.value)
        traverse(node.right)
    }

    traverse(tree.root)
    return result.toList()
}
