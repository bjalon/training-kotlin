package eu.centralpay.domain

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class CellTest {
    @Test
    fun `should init Cell`() {
        assertNotNull(Cell("cellName"))
    }

    @Test
    fun `should return null if Cell has no next Cell`() {
        assertEquals("cellName", Cell("cellName").next.name)
    }

    @Test
    fun `should return next Cell if has next Cell`() {
        assertNotNull("cellNameNext", Cell("cellNamePrevious", Cell("cellNameNext")).next.name)
    }
}