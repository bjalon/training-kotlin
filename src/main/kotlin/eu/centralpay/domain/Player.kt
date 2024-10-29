package eu.centralpay.domain

class Player(val name: String, var currentCell: Cell) {
    fun play(cup: Cup) {
        cup.roll()
        val moveValue = cup.value
        println("${name} has rolled $moveValue")

        currentCell = (moveValue downTo 1).fold(currentCell) { acc, _ -> acc.next }

        println("\t${name} is now on ${currentCell.name}")
    }
}
