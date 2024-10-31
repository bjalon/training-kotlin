package eu.centralpay.domain.cells

import eu.centralpay.domain.Cell
import eu.centralpay.domain.Player
import eu.centralpay.domain.exceptions.UserWonException
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class FinalCellTest {

    private lateinit var cell: Cell
    private lateinit var player: Player
    private lateinit var player2: Player

    @BeforeEach
    fun setUp() {
        player = mockk<Player>().apply {}
        cell = FinalCell(name = "FinalCell")
    }

    @Test
    fun `should init`() {
        assertNotNull(cell)
    }

    @Test
    fun `player should throw UserWon exception on final cell`() {

        val e = assertThrows<UserWonException> {  cell.action(player = player, mockk()) }
        assertEquals(e.player, player)
    }
}