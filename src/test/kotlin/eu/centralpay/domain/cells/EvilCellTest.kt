package eu.centralpay.domain.cells

import eu.centralpay.domain.Cell
import eu.centralpay.domain.Player
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull

class EvilCellTest {

    @Test
    fun `should init`() {
        assertNotNull(MyEvilCellForTest(name = "EvilCell"))
    }

    @Test
    fun `player should lost one piece on evil cell`() {
        val player = mockk<Player>().apply { every { lostOnePiece() } returns Unit }
        val cell = MyEvilCellForTest(name = "EvilCell")

        verify(exactly = 0) { player.lostOnePiece() }
        cell.action(player = player, mockk())
        verify(exactly = 1) { player.lostOnePiece() }
    }
    
    private class MyEvilCellForTest(name: String, next: Cell? = null): EvilCell(name, next)
}