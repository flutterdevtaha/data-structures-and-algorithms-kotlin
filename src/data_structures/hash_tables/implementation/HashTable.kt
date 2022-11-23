package data_structures.hash_tables.implementation

import java.util.*

data class KeyValue(val key: String, val value: Int)

class HashTable(size: Int) {
    val data: Array<ArrayList<KeyValue>?> = arrayOfNulls<ArrayList<KeyValue>?>(size)
    private var currentLength: Int = 0

    private fun hash(key: String): Int {
        var hash = 0
        for (i in key.indices) {
            hash = (hash + key.codePointAt(i) * i) % data.size
        }
        return hash
    }

    operator fun set(key: String, value: Int) {
        val address = hash(key)
        if (data[address] == null) {
            val arrayAtAddress = ArrayList<KeyValue>()
            data[address] = arrayAtAddress
            currentLength++
        }
        val pair = KeyValue(key, value)
        data[address]?.add(pair)
    }

    operator fun get(key: String): Int? {
        val address = hash(key)
        val bucket = data[address]
        if (bucket != null) {
            println("bucket: $bucket")
            for (keyValue in bucket) {
                if (keyValue.key == key) {
                    return keyValue.value
                }
            }
        }
        return null
    }


    fun keys(): Array<String?>? {
        val bucket = data
        var keysArray: Array<String?>? = null
        var count = 0
        for (keyValues in bucket) {
            if (keyValues != null) {
                if (keyValues.size > 1) {
                    keysArray = arrayOfNulls(keyValues.size)
                    for (keyValue in keyValues) {
                        keysArray[count] = keyValue.key
                        count++
                    }
                } else {
                    keysArray = arrayOfNulls(currentLength)
                    keysArray[count] = keyValues[0].key
                    count++
                }
            }
        }
        return keysArray
    }
}

fun main() {
    val hashTable = HashTable(2)
    hashTable["grapes"] = 1200
    hashTable["grapes"] = 1201
    hashTable["grapes"] = 1202
//    hashTable["apple"] = 1500
//    println("value for key grapes: " + hashTable["grapes"])
//    println("value for key apple: " + hashTable["apple"])
    println("hashTable.data: " + hashTable.data.contentToString())
    println("list of keys: " + hashTable.keys().contentToString())
}