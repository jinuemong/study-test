package com.studytest.datastruct.linkedlist

class SingleLinkedList<T> {
    private var head: Node<T>? = null
    private var size: Int = 0

    fun size(): Int = size

    fun isEmpty(): Boolean = size == 0

    fun addFirst(value: T) {
        val newNode = Node(value, head)
        head = newNode
        size++
    }

    fun addLast(value: T) {
        if (isEmpty()) return addFirst(value)

        var current = head
        while (current?.next != null) {
            current = current.next
        }
        current?.next = Node(value)
        size++
    }

    fun addAt(index: Int, value: T) {
        if (index !in 0..<size) throw IndexOutOfBoundsException()
        if (index == 0) return addFirst(value)

        var current = head
        for (i in 0 until index-1) current = current?.next

        current?.next = Node(value, current?.next)
        size++
    }

    fun remove(value: T): T? {
        var current = head
        var prev: Node<T>? = null

        while (current != null) {
            if (current.value == value) {
                if (prev == null) {
                    head = current.next
                } else {
                    prev.next = current.next
                }
                size--
                return current.value
            }
            prev = current
            current = current.next
        }
        return null
    }

    fun get(index:Int): T? {
        if (index !in 0..<size) return null
        var current = head
        for (i in 0 until index){
            current = current?.next
        }
        return current?.value
    }

}
