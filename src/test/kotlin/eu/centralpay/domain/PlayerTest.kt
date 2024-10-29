package eu.centralpay.domain

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class PlayerTest {

    private lateinit var cup: Cup

    @BeforeEach
    fun setUp() {
        cup = mockk<Cup>()
        every { cup.roll() } returns Unit
    }

    @Test
    fun `should initialize`() {
        val player = Player(name = "playerName", currentCell = Cell("cellName"))
        assertNotNull(player)
        assertEquals("playerName", player.name)
        assertEquals("cellName", player.currentCell.name)
    }

    @Test
    fun `should move to the next Cell if ask move to 1`() {
        every { cup.value } returns 1

        val initialCell = Cell("initialCell", Cell("nextCell"))
        val player = Player(name = "playerName", currentCell = initialCell)

        player.play(cup)
        assertEquals("nextCell", player.currentCell.name)
    }

    @Test
    fun `should move in board cells`() {

        for (i in 1..12) {
            every { cup.value } returns i

            val board = Board()
            val player = Player(name = "playerName", currentCell = board.startCell)

            player.play(cup)
            assertEquals("Cell $i", player.currentCell.name)
        }
    }
}