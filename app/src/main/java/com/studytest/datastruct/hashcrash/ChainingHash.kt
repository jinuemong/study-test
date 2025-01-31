package com.studytest.datastruct.hashcrash

// 해시 충돌을 방지하기 위한 해시 + 연결리스트
// 배열의 각 인덱스가 연결 리스트를 가지는 구조
class ChainingHash<K,V>(
    private val capacity: Int = 10
){
    private val table = Array<MutableList<Pair<K,V>>>(capacity){
        mutableListOf()
    }

    // key에 따른 hash값 저장
    private fun hash(key: K) = key.hashCode() % capacity

    fun put(key: K, value: V){
        val index = hash(key)
        val bucket = table[index]

        // 같은 키가 있으면 값 업데이트 (키가 같고, 해시가 같다 -> 같은 값을 업데이트 해야함)
        for (i in bucket.indices){
            if (bucket[i].first == key){
                bucket[i] = key to value
                return
            }
        }
        // 같은 키가 없으면 리스트에 추가
        bucket.add(key to value)
    }

    fun get(key: K) : V? {
        val index=  hash(key)
        val bucket = table[index]

        for ((k,v) in bucket){
            if (k == key) return v
        }
        return null
    }
}
