package com.studytest.datastruct.hashcrash

// 해시 충돌 시 빈 슬롯을 찾아 순차적으로 이동
// 클러스터링 현상(연속적인 충돌)이 발생할 수 있음
class LinearProbingHash<K, V>(private val capacity: Int = 10) {
    private val table = arrayOfNulls<Pair<K, V>?>(capacity)

    private fun hash(key: K) = key.hashCode() % capacity

    fun put(key: K, value: V) {
        var index = hash(key)
        // table[index] == null이라면 빈 테이블
        while (table[index] != null && table[index]?.first != key) {
            index = (index + 1) % capacity
        }
        // 빈 테이블에 추가
        table[index] = key to value
    }

    fun get(key: K): V? {
        var index = hash(key)
        while (table[index] != null) {
            if (table[index]?.first == key) return table[index]?.second
            index = (index + 1) % capacity
        }
        return null
    }
}
