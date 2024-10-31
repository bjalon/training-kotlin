package eu.centralpay.domain.cells

import eu.centralpay.domain.Cell
import eu.centralpay.domain.Player
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull

class FrozenCellTest {
    private lateinit var cell: Cell
    private lateinit var player: Player

    @BeforeEach
    fun setUp() {
        player = mockk<Player>().apply {
            every { pieceCount } returns 20
            every { lostOnePiece() } returns Unit
            every { standbyOnNextRound(any()) } returns Unit
        }
    }

    @Test
    fun `should init`() {
        cell = HostelCell(name = "HostelCell")
        assertNotNull(cell)
    }

    @Test
    fun `player should lost one piece and given turn on frozen cell`() {
        verify(exactly = 0) { player.lostOnePiece() }
        verify(exactly = 0) { player.standbyOnNextRound(any()) }
        val cell = object : FrozenCell("Frozen Cell", 10) {}
        cell.action(player = player, mockk())
        verify(exactly = 1) { player.lostOnePiece() }
        verify(exactly = 1) { player.standbyOnNextRound(any()) }
        verify(exactly = 1) { player.standbyOnNextRound(10) }
    }

    @Test
    fun `player should lost one piece and one turn on hostel cell`() {
        HostelCell("Hostel Cell").action(player = player, mockk())
        verify(exactly = 1) { player.standbyOnNextRound(1) }
    }

    @Test
    fun `player should lost one piece and 2 turn on well cell`() {
        WellCell("Well Cell").action(player = player, mockk())
        verify(exactly = 1) { player.standbyOnNextRound(2) }
    }
}