package data_structures.linked_lists.singly_linked_list

class LinkedList(value: Int) {
    private var head: Node? = Node(value)
    private var tail: Node? = head
    var length: Int = 1

    fun append(value: Int) {
        val newNode = Node(value)
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
            var current: Node? = head
            for (i in 0 until index - 1) {
                current = current?.next
            }
            val newNode = Node(value)
            newNode.next = current?.next
            current?.next = newNode
            length++
        }
    }

    fun remove(index: Int) {
        if (index < 0 || index > length) {
            println("Index Out Of Bounds For Length $length")
        } else if (index == 0) {
            head = head?.next
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
            }
        }
    }

    fun reverse(linkedList: LinkedList): LinkedList {
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

fun main() {
    val myLinkedList = LinkedList(10)
    //appending
    myLinkedList.append(12)
    myLinkedList.append(16)
    //prepending
    myLinkedList.prepend(20)
    myLinkedList.prepend(50)
    println("length: " + myLinkedList.length)
    println("list: " + myLinkedList.printList().contentToString())
    //inserting
    myLinkedList.insert(2, 25)
    println("length: " + myLinkedList.length)
    println("list: " + myLinkedList.printList().contentToString())
    //removing
    myLinkedList.remove(4)
    println("length: " + myLinkedList.length)
    println("list: " + myLinkedList.printList().contentToString())
    //reversing
    val linkedList2 = myLinkedList.reverse(myLinkedList)
    println("reverse linkedList" + linkedList2.printList().contentToString())
}

/*
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

    fun removeAt(index: Int) {
        val leader: Node? = traverseToIndex(index - 1)
        val unwantedNode: Node? = leader?.next
        leader?.next = unwantedNode?.next
        length--
    }


}
*/