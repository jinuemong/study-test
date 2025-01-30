package com.studytest.datastruct.hashcrash

// 충돌 발생 시 두 번째 해시 값만큼 점프하여 탐색
// 충돌 방지가 뛰어나지만, 두 개의 해시 함수를 잘 설계해야 함
class DoubleHashingHash<K, V>(private val capacity: Int = 10) {
    private val table = arrayOfNulls<Pair<K, V>?>(capacity)

    private fun hash1(key: K) = key.hashCode() % capacity
    // 충돌 시 이동할 간격을 계산하며, 0방지를 위해 1 더해줌
    private fun hash2(key: K) = 1 + (key.hashCode()) % (capacity - 1)

    fun put(key: K, value: V) {
        var index = hash1(key)
        val step = hash2(key)

        // while안에서 걸릴 경우, 충돌이 발생했으므로 step 활용
        while (table[index] != null && table[index]?.first != key) {
            index = (index + step) % capacity
        }
        table[index] = key to value
    }

    fun get(key: K): V? {
        var index = hash1(key)
        val step = hash2(key)
        while (table[index] != null) {
            if (table[index]?.first == key) return table[index]?.second
            index = (index + step) % capacity
        }
        return null
    }
}
