package eu.centralpay.domain

import eu.centralpay.domain.cells.*

class Board(val cellCount: Int = 63) {

    val startCell: Cell
        get() = cells[1]!!

    val cells = HashMap<Int, Cell>()

    init {
        (1..cellCount).map { if (cells[it] == null) generateCell(it) }
        (1 until cellCount).forEach { cells[it]!!.next = cells[it + 1]!! }
    }

    fun getCell(index: Int): Cell = cells[index] ?: throw UnsupportedOperationException()

    private fun generateCell(index: Int): Cell {
        val cell = when (index) {
            9, 18, 27, 36, 45, 54 -> GooseCell("Cell $index (Goose)")
            6 -> BridgeCell("Cell $index (Bridge)").apply { linkedCell = generateCell(12) }
            52 -> JailCell("Cell $index (Jail)")
            19 -> HostelCell("Cell $index (Hostel)")
            63 -> FinalCell("Cell $index (Final)")
            else -> Cell("Cell $index")
        }
        cells[index] = cell
        return cell
    }
}
