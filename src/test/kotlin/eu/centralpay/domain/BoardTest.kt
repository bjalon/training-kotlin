package eu.centralpay.domain

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class BoardTest {
    @Test
    fun `should init`() {
        assertNotNull(Board())
    }

    @Test
    fun `should get first cell`() {
        assertEquals("Start", Board().startCell.name)
    }

    @Test
    fun `should get next cells`() {
        var currentCell = Board().startCell

        for (i in 1..12) {
            assertEquals("Cell $i", currentCell.next.name)
            currentCell = currentCell.next
        }
    }
}