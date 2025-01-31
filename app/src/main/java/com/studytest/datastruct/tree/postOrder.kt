package com.studytest.datastruct.tree

fun<T> postOrder(
    tree: Tree<T>
): List<T> {
    val result = mutableListOf<T>()

    fun traverse(node: Node<T>?){
        node ?: return

        traverse(node.left)
        traverse(node.right)
        result.add(node.value)
    }

    traverse(tree.root)
    return result.toList()
}
