package data_structures.linked_lists.doubly_linked_list

class DoublyLinkedList(value: Int) {
    var head: Node? = Node(value)
    var tail: Node? = head
    var length: Int = 1

    fun append(value: Int) {
        val newNode = Node(value)
        newNode.previous = tail
        tail?.next = newNode
        tail = newNode
        length++
    }

    fun prepend(value: Int) {
        val newNode = Node(value)
        head?.previous = newNode
        newNode.next = head
        head = newNode
        length++
    }

    fun printList(): IntArray {
        val myList = IntArray(length)
        var current: Node? = head
        var i = 0
        while (current != null) {
            myList[i] = current.value
            current = current.next
            i++
        }
        return myList
    }

    fun insert(index: Int, value: Int) {
        if (index < 0 || index > length) {
            println("Index Out Of Bounds For Length $length")
        } else if (index == 0) {
            prepend(value)
        } else if (index == length) {
            append(value)
        } else {
            var current: Node? = head
            for (i in 0 until index - 1) {
                current = current?.next
            }
            val newNode = Node(value)
            newNode.next = current?.next
            current?.next = newNode
            newNode.previous = current
            newNode.next?.previous = newNode
            length++
        }
    }

    fun remove(index: Int) {
        if (index < 0 || index > length) {
            println("Index Out Of Bounds For Length $length")
        } else if (index == 0) {
            head = head?.next
            head?.previous = null
            length--
        } else {
            var current: Node? = head
            var i = 0
            while (i < index - 1) {
                current = current?.next
                i++
            }
            current?.next = current?.next?.next
            length--
            if (i == length - 1) {
                tail = current
            } else {
                current?.next?.previous = current
            }
        }
    }
}

fun main() {
    val myDoublyLinkedList = DoublyLinkedList(5)
    myDoublyLinkedList.append(3)
    myDoublyLinkedList.append(4)
    myDoublyLinkedList.prepend(2)
    myDoublyLinkedList.prepend(1)
    println(myDoublyLinkedList.printList().contentToString())
    myDoublyLinkedList.remove(0)
    println(myDoublyLinkedList.printList().contentToString())
    myDoublyLinkedList.insert(2, 200)
    println(myDoublyLinkedList.printList().contentToString())
    println("length: " + myDoublyLinkedList.length)
    println("head value: " + myDoublyLinkedList.head?.value)
    println("head.previous: " + myDoublyLinkedList.head?.previous)
    println("tail value: " + myDoublyLinkedList.tail?.value)
    println("tail.next: " + myDoublyLinkedList.tail?.next)
}

/*
class DoublyLinkedList(value: Int) {
    private var head: Node? = Node(value)
    private var tail: Node? = head
    var length: Int = 1
    fun append(value: Int) {
        val newNode = Node(value)
        newNode.prev = tail
        tail?.next = newNode
        tail = newNode
        length++
    }

    fun prepend(value: Int) {
        val newNode = Node(value)
        head?.prev = newNode
        newNode.next = head
        head = newNode
        length++
    }

    fun printList(): IntArray {
        val myList = IntArray(length)
        var current: Node? = head
        var i = 0
        while (current != null) {
            myList[i] = current.value
            current = current.next
            i++
        }
        return myList
    }

    fun insert(index: Int, value: Int) {
        if (index < 0 || index > length) {
            println("Index Out Of Bounds For Length $length")
        } else if (index == 0) {
            prepend(value)
        } else if (index == length) {
            append(value)
        } else {
            val newNode = Node(value)
            val leader: Node? = traverseToIndex(index - 1)
            val holdingPointer: Node? = leader?.next
            leader?.next = newNode
            newNode.prev = leader
            newNode.next = holdingPointer
            holdingPointer?.prev = newNode
            length++
        }
    }

    private fun traverseToIndex(index: Int): Node? {
        var current: Node? = head
        var counter = 0
        while (counter != index) {
            current = current?.next
            counter++
        }
        return current
    }

    fun reverseList(): IntArray {
        val myList = IntArray(length)
        var current: Node? = tail
        var i = 0
        while (current != null) {
            myList[i] = current.value
            current = current.prev
            i++
        }
        return myList
    }

    fun remove(index: Int) {
        val leader: Node? = traverseToIndex(index - 1)
        val unwantedNode: Node? = leader?.next
        leader?.next = unwantedNode?.next
        unwantedNode?.next?.prev = leader
        length--
    }

}

data class Node(var value: Int, var next: Node? = null, var prev: Node? = null)



*/