package eu.centralpay.domain

class Cell(val name: String, next: Cell? = null) {
    val next: Cell = next ?: this
}
