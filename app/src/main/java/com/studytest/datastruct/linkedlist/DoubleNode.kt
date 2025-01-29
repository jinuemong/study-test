package com.studytest.datastruct.linkedlist

data class DoubleNode<T>(
    var value: T,
    var prev : DoubleNode<T>?=null,
    var next : DoubleNode<T>?=null,
)
