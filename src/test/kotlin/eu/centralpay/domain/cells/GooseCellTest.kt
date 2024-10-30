package eu.centralpay.domain.cells

import eu.centralpay.domain.Cell
import eu.centralpay.domain.Cup
import eu.centralpay.domain.Player
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.nio.file.Files.move
import kotlin.test.assertNotNull

class GooseCellTest {
    private lateinit var cell: Cell
    private lateinit var player: Player

    @BeforeEach
    fun setUp() {
        player = mockk<Player>().apply {
            every { moveAgain(any()) } returns Unit
        }
        cell = GooseCell(name = "GooseCell")
    }

    @Test
    fun `should init`() {
        assertNotNull(cell)
    }

    @Test
    fun `player should move again on goose cell`() {
        val cup = mockk<Cup>().apply { every { value } returns 1 }

        val cell = GooseCell(name = "GooseCell")

        verify(exactly = 0) { player.moveAgain(any()) }
        cell.action(player = player, cup)
        verify(exactly = 1) { player.moveAgain(any()) }
    }

}