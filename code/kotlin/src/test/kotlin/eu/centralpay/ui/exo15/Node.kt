package eu.centralpay.ui.exo15

sealed interface INode

class EmptyNode : INode

data class Node(
    val value: Int,
    var left: INode = EmptyNode(),
    var right: INode = EmptyNode(),
) : INode

class BinaryTree(
    var root: INode = EmptyNode()
) {
    fun add(value: Int) {
        root = addRecursive(root, value)
    }

    fun addRecursive(node: INode, value: Int): INode {
        when {
            node is EmptyNode -> return Node(value)
            node is Node -> when {
                value < node.value -> node.left = addRecursive(node.left, value)
                value > node.value -> node.right = addRecursive(node.right, value)
            }
        }
        return node
    }

    fun contains(value: Int) = containsNodeRecursive(root, value)

    fun containsNodeRecursive(node: INode, value: Int): Boolean {
        when {
            node is EmptyNode -> return false
            node is Node -> when {
                value == node.value -> return true
                value < node.value -> return containsNodeRecursive(node.left, value)
                else -> return containsNodeRecursive(node.right, value)
            }
            else -> throw IllegalStateException()
        }
    }
}

fun main() {
    val bt = BinaryTree()
    bt.add(6)
    bt.add(4)
    bt.add(8)
    bt.add(3)
    bt.add(5)
    bt.add(7)
    bt.add(9)
    println(bt.contains(5)) // true
    println(bt.contains(1)) // false
}