package eu.centralpay.domain

open class Cell(val name: String, next: Cell? = null) {
    var next: Cell = next ?: this

    open fun action(player: Player, cup: Cup) {
        println("  No special behavior")
    }
}
