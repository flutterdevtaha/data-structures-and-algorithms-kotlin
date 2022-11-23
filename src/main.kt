fun main() {

    val doublyLinkedList = DoublyLinkedList(1)
    doublyLinkedList.append(5)
    doublyLinkedList.prepend(15)
    doublyLinkedList.append(10)
    doublyLinkedList.insert(2, 99)
    doublyLinkedList.remove(2)

    println(doublyLinkedList.printList().contentToString())
    println(doublyLinkedList.reverseList().contentToString())
}

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

