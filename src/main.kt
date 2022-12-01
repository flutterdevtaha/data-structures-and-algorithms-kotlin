fun main() {
    println("Hello, world!")
    val linkedList = LinkedList(5)
    linkedList.append(6)
    linkedList.append(15)
    linkedList.remove(1)
    linkedList.prepend(10)
    linkedList.insert(1, 20)
    println(linkedList.printList().contentToString())
    linkedList.reverse(linkedList)
    println(linkedList.printList().contentToString())
}


class LinkedList(value: Int) {
    private var head: Node? = Node(value)
    private var tail: Node? = head
    private var length: Int = 1

    fun append(value: Int) {
        val newNode = Node(value)
        tail?.next = newNode
        tail = newNode
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

    fun prepend(value: Int) {
        val newNode = Node(value)
        newNode.next = head
        head = newNode
        length++
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
            newNode.next = holdingPointer
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

    fun remove(index: Int) {
        val leader: Node? = traverseToIndex(index - 1)
        val unwantedNode: Node? = leader?.next
        leader?.next = unwantedNode?.next
        length--
    }


    fun reverse() {
        if (head?.next != null) {
            return head
        }
        val newList = LinkedList(linkedList.head!!.value)
        var current: Node? = linkedList.head
        while (current?.next != null) {
            current = current.next
            val newNode = Node(current!!.value)
            newNode.next = newList.head
            newList.head = newNode
            newList.length++
        }
        return newList
    }
}

class Node(var value: Int) {
    var next: Node? = null
}





