package eu.centralpay.domain

import eu.centralpay.domain.cells.BridgeCell
import eu.centralpay.domain.cells.FinalCell
import eu.centralpay.domain.cells.GooseCell
import eu.centralpay.domain.cells.HostelCell
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class BoardTest {
    @Test
    fun `should init`() {
        assertNotNull(Board())
    }

    @Test
    fun `should get first cell`() {
        assertEquals("Cell 1", Board().startCell.name)
    }

    @Test
    fun `should get next cells`() {
        var currentCell = Board().startCell

        for (i in 1..12) {
            assertTrue(currentCell.next.name.startsWith("Cell ${i + 1}"))
            currentCell = currentCell.next
        }
    }

    @Test
    fun `should get cells index`() {
        val board = Board()

        for (i in 1..12) {
            if (i !in listOf(9, 18, 27, 36, 45, 54, 6, 19, 63))
                assertTrue(board.getCell(i).name.startsWith("Cell $i"), "Expected ${board.getCell(i).name} starts with Cell $i")
        }
    }

    @Test
    fun `should 9s cells be goose cells`() {
        val board = Board()

        assertTrue(board.getCell(9) is GooseCell)
        assertTrue(board.getCell(18) is GooseCell)
        assertTrue(board.getCell(27) is GooseCell)
        assertTrue(board.getCell(36) is GooseCell)
        assertTrue(board.getCell(45) is GooseCell)
        assertTrue(board.getCell(54) is GooseCell)
    }

    @Test
    fun `should 6 cell be a bridge cell`() {
        val board = Board()

        val cell = board.getCell(6)
        assertTrue(cell is BridgeCell, "Expected ${cell::class.java.name} to be a BridgeCell")
        assertEquals("Cell 12", cell.linkedCell.name)
    }

    @Test
    fun `should 19 cell be a hostel cell`() {
        val board = Board()

        val cell = board.getCell(19)
        assertTrue(cell is HostelCell, "Expected ${cell::class.java.name} to be a HostelCell")
    }

    @Test
    fun `should 63 cell be a final cell`() {
        val board = Board()

        val cell = board.getCell(63)
        assertTrue(cell is FinalCell, "Expected ${cell::class.java.name} to be a FinalCell")
    }
}