package eu.centralpay.domain.cells

import eu.centralpay.domain.Cell
import eu.centralpay.domain.Player
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull

class JailCellTest {

    private lateinit var cell: Cell
    private lateinit var player1: Player
    private lateinit var player2: Player

    @BeforeEach
    fun setUp() {
        player1 = mockk<Player>().apply {
            every { lostOnePiece() } returns Unit
            every { freeze() } returns Unit
            every { unFreeze() } returns Unit
        }
        player2 = mockk<Player>().apply {
            every { lostOnePiece() } returns Unit
            every { freeze() } returns Unit
            every { unFreeze() } returns Unit
        }
        cell = JailCell(name = "JailCell")
    }

    @Test
    fun `should init`() {
        assertNotNull(cell)
    }

    @Test
    fun `player should freeze and lost piece on jail cell`() {

        val cell = JailCell(name = "JailCell")

        verify(exactly = 0) { player1.lostOnePiece() }
        verify(exactly = 0) { player1.freeze() }
        cell.action(player = player1, mockk())
        verify(exactly = 1) { player1.lostOnePiece() }
        verify(exactly = 1) { player1.freeze() }
    }

    @Test
    fun `previous player frozen should be unfrozen if another player frozen`() {
        cell.action(player = player1, mockk())
        cell.action(player = player2, mockk())

        verify(exactly = 1) { player1.unFreeze() }
        verify(exactly = 1) { player2.lostOnePiece() }
        verify(exactly = 1) { player2.freeze() }
    }

}