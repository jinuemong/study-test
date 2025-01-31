package com.studytest.datastruct.tree

data class Tree<T>(val root: Node<T>?)

data class Node<T>(
    var left : Node<T>?,
    var right : Node<T>?,
    var value : T,
)
