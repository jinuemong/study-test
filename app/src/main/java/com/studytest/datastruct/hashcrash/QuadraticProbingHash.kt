package com.studytest.datastruct.hashcrash

// 선형 조사법에 비해 클러스터링은 줄어들었지만, 저장 공간이 부족하면 큰 문제 발생
class QuadraticProbingHash<K,V>(private val capacity: Int = 10) {
    private val table = arrayOfNulls<Pair<K,V>?>(capacity)

    private fun hash(key: K) = key.hashCode() % capacity

    fun put(key: K, value : V){
        var index = hash(key)
        var i = 1
        while(table[index] != null && table[index]?.first != key){
            index = (index + i*i)%capacity
            i++
        }
        table[index] = key to value
    }

    fun get(key: K): V? {
        var index = hash(key)
        var i = 1
        while (table[index] != null){
            if (table[index]?.first == key) return table[i]?.second
            index = (index + i*i) % capacity
            i++
        }
        return null
    }
}
