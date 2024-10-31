package eu.centralpay.domain

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

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
        assertEquals(20, player.pieceCount)
        assertFalse(player.isFrozen)
        assertEquals(0, player.standbyRoundCount)
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

        for (i in 1..4) {
            every { cup.value } returns i

            val board = Board()
            val player = Player(name = "playerName", currentCell = board.startCell)

            player.play(cup)
            assertTrue(
                player.currentCell.name.startsWith("Cell ${i + 1}"),
                "Expected ${player.currentCell.name} starts with Cell $i"
            )
        }
    }

    @Test
    fun `should lost one piece if one piece is lost`() {
        val player = Player(name = "playerName", currentCell = Cell("cellName"))

        player.lostOnePiece()

        assertEquals(19, player.pieceCount)
    }

    @Test
    fun `should move again if move again called`() {
        val player = Player(name = "playerName", currentCell = Cell("init cell", Cell("cell 1", Cell("cell 2"))))

        every { cup.value } returns 1
        assertEquals("init cell", player.currentCell.name)

        player.play(cup)
        assertEquals("cell 1", player.currentCell.name)

        player.moveToCell(cup)
        assertEquals("cell 2", player.currentCell.name)
    }

    @Test
    fun `should don't move if player is on standby`() {
        val player = Player(name = "playerName", currentCell = Cell("init cell", Cell("cell 1", Cell("cell 2"))))

        player.standbyOnNextRound()
        player.play(cup)

        assertEquals("init cell", player.currentCell.name)
    }

    @Test
    fun `should don't move twice if player is on standby twice`() {
        val player = Player(name = "playerName", currentCell = Cell("init cell", Cell("cell 1", Cell("cell 2"))))

        player.standbyOnNextRound(standbyRoundCount = 2)
        player.play(cup)
        player.play(cup)

        assertEquals("init cell", player.currentCell.name)
    }

    @Test
    fun `should move after the second round if player is on standby`() {
        val player = Player(name = "playerName", currentCell = Cell("init cell", Cell("cell 1", Cell("cell 2"))))
        every { cup.value } returns 1

        player.standbyOnNextRound()
        player.play(cup)
        player.play(cup)

        assertEquals("cell 1", player.currentCell.name)
    }

    @Test
    fun `should move on third round if player is on standby twice`() {
        val player = Player(name = "playerName", currentCell = Cell("init cell", Cell("cell 1", Cell("cell 2"))))
        every { cup.value } returns 1

        player.standbyOnNextRound(standbyRoundCount = 2)
        assertEquals(2, player.standbyRoundCount)
        player.play(cup)
        player.play(cup)
        player.play(cup)

        assertEquals(0, player.standbyRoundCount)
        assertEquals("cell 1", player.currentCell.name)
    }

    @Test
    fun `should freeze if player freeze`() {
        val player = Player(name = "playerName", currentCell = Cell("init cell", Cell("cell 1")))

        player.freeze()
        player.play(cup)
        player.play(cup)
        player.play(cup)

        assertEquals("init cell", player.currentCell.name)
        assertTrue(player.isFrozen)
    }

    @Test
    fun `should unfreeze if player freeze`() {
        val player = Player(name = "playerName", currentCell = Cell("init cell", Cell("cell 1")))

        every { cup.value } returns 1

        player.freeze()
        player.unFreeze()
        player.play(cup)

        assertEquals("cell 1", player.currentCell.name)
        assertFalse(player.isFrozen)
    }
}