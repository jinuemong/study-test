package com.studytest.datastruct.linkedlist

class DoubleLinkedList<T> {
    private var head: DoubleNode<T>? = null
    private var tail: DoubleNode<T>?=null
    private var size: Int = 0

    fun size() : Int = size

    fun isEmpty() : Boolean = size == 0

    fun addFirst(value: T){
        val newNode = DoubleNode(value)
        if (isEmpty()){
            head = newNode
            tail = newNode
        } else {
            newNode.next = head
            head?.prev = newNode
            head = newNode
        }
        size++
    }

    fun addLast(value: T){
        val newNode = DoubleNode(value)
        if (isEmpty()){
            head = newNode
            tail = newNode
        } else {
            newNode.prev = tail
            tail?.next = newNode
            tail = newNode
        }
        size++
    }

    fun addAt(index: Int, value: T){
        if (index !in 0..size) throw IndexOutOfBoundsException()
        if(index == 0) return addFirst(value)
        if (index == size) addLast(value)

        var current = head
        for (i in 0 until index){
            current = current?.next
        }
        val newNode = DoubleNode(value,current?.prev,current)
        current?.prev?.next = newNode
        current?.prev = newNode
        size++
    }

    fun remove(value: T): Boolean{
        var current = head
        while (current != null){
            if (current.value ==value){
                if (current == head){
                    head = current.next
                    head?.prev = null
                } else if (current == tail){
                    tail = current.prev
                    tail?.next = null
                } else {
                    current.prev?.next = current.next
                    current.next?.prev = current.prev
                }
                size--
                return true
            }
            current = current.next
        }
        return false
    }

    fun get(index: Int): T? {
        if (index !in 0..<size) return null
        var current = head
        for (i in 0 until  index){
            current = current?.next
        }
        return current?.value
    }
}
