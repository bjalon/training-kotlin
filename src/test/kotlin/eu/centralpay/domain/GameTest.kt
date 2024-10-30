package eu.centralpay.domain

import eu.centralpay.domain.exceptions.UserWonException
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class GameTest {
    lateinit var playerNames: List<String>
    lateinit var game: Game
    lateinit var cup: Cup

    @BeforeEach
    fun setUp() {
        cup = mockk()
        playerNames = listOf("Benjamin", "Fadoua", "Bénédicte", "Reece")
        game = Game(playerNames = playerNames, cup = cup)

    }

    @Test
    fun `should init`() {
        assertNotNull(game)
        assertEquals(4, game.players.size)
        assertEquals(playerNames, game.players.map { it.name })
    }

    @Test
    fun `should play first round`() {
        every { cup.roll() } returns Unit
        every { cup.value } returnsMany listOf(1, 2, 3, 4)

        game.doRound()
        assertEquals("Cell 2", game.players[0].currentCell.name)
        assertEquals("Cell 3", game.players[1].currentCell.name)
        assertEquals("Cell 4", game.players[2].currentCell.name)
        assertEquals("Cell 5", game.players[3].currentCell.name)
        assertTrue(game.isInProgress)
        assertNull(game.winner)
    }

    @Test
    fun `should throw exception if user has won`() {
        every { cup.roll() } returns Unit
        every { cup.value } returnsMany listOf(1, 2, 3, game.board.cellCount)

        val e = assertThrows<UserWonException> { game.doRound() }

        assertEquals("Reece", e.player.name)
    }

    @Test
    fun `should play until a user has won`() {
        var roundIndex = 0

        every { cup.roll() } returns Unit
        every { cup.value } answers { roundIndex ++; roundIndex % 4 + 1 }

        game.playUntilAUserHasWon()

        assertFalse(game.isInProgress)
        assertNotNull(game.winner)
        assertEquals("Benjamin", game.winner!!.name)
    }
}