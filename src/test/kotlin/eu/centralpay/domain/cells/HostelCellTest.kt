package eu.centralpay.domain.cells

import eu.centralpay.domain.Cell
import eu.centralpay.domain.Player
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull

class HostelCellTest {
    private lateinit var cell: Cell
    private lateinit var player: Player

    @BeforeEach
    fun setUp() {
        player = mockk<Player>().apply {
            every { lostOnePiece() } returns Unit
            every { standbyOnNextRound() } returns Unit
        }
        cell = HostelCell(name = "HostelCell")
    }

    @Test
    fun `should init`() {
        assertNotNull(cell)
    }

    @Test
    fun `player should lost one piece on evil cell`() {
        verify(exactly = 0) { player.lostOnePiece() }
        verify(exactly = 0) { player.standbyOnNextRound() }
        cell.action(player = player, mockk())
        verify(exactly = 1) { player.lostOnePiece() }
        verify(exactly = 1) { player.standbyOnNextRound() }
    }

}