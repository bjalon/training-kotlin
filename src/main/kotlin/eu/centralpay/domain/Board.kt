package eu.centralpay.domain

class Board(val cellCount: Int = 63) {

    val startCell: Cell
    val cells = mutableMapOf<Int, Cell>()

    init {
        startCell = initCellWithNextIndex(name = "Start", lastCellIndex = cellCount)
    }

    private fun initCellWithNextIndex(name: String, lastCellIndex: Int, index: Int = 0): Cell =
        if (index != lastCellIndex) {
            val nextIndex = index + 1
            Cell(name = name, next = initCellWithNextIndex("Cell $nextIndex", lastCellIndex, nextIndex)).apply { cells[index] = this }
        } else
            Cell(name).apply { cells[index] = this }
}
