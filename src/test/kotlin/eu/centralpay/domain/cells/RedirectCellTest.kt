package eu.centralpay.domain.cells

import eu.centralpay.domain.Cell
import eu.centralpay.domain.Player
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull

class RedirectCellTest {
    private lateinit var cell: Cell
    private lateinit var player: Player

    @BeforeEach
    fun setUp() {
        player = mockk<Player>().apply {
            every { pieceCount } returns 20
            every { lostOnePiece() } returns Unit
            every { moveToCell(targetCell = any(), any()) } returns Unit
        }
        cell = RedirectCell(name = "RedirectCell").apply { linkedCell = Cell("LinkedCell") }
    }

    @Test
    fun `should init`() {
        assertNotNull(cell)
    }

    @Test
    fun `player should lost one piece and go to linked cell when bridge cell is touched`() {
        verify(exactly = 0) { player.lostOnePiece() }
        verify(exactly = 0) { player.moveToCell(targetCell = any(), any()) }
        cell.action(player = player, mockk())
        verify(exactly = 1) { player.lostOnePiece() }
        verify(exactly = 1) { player.moveToCell(targetCell = any(), any()) }
    }

}