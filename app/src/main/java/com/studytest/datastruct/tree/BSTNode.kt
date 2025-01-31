package com.studytest.datastruct.tree

//이진 탐색 트리
class BSTNode<T: Comparable<T>>(
    var value : T,
    var left: BSTNode<T>? = null,
    var right: BSTNode<T> ? = null,
)

