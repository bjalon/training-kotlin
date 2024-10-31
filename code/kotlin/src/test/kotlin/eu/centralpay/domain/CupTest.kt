package eu.centralpay.domain

import eu.centralpay.domain.exceptions.CupNotRolledException
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class CupTest {

    @Test
    fun `should create Cup`() {
        assertNotNull(Cup())
    }

    @Test
    fun `should throw exception if cup not rolled and get value`() {
        val cup = Cup()
        assertThrows<CupNotRolledException> { cup.value }
    }

    @Test
    fun `should return cup roll result if cup rolled`() {
        val cup = Cup()
        cup.roll()
        assertTrue(cup.value > 0)
        assertTrue(cup.value < 12)
    }

    @Test
    fun `should return sum of dice if cup rolled`() {
        val diceMock = mockk<Dice>()

        every { diceMock.value } returnsMany  listOf(1, 2, 3, 4, 5, 6)
        every { diceMock.roll() } returns Unit

        val cup = Cup(listOf(diceMock, diceMock))

        cup.roll()
        assertEquals(3, cup.value)

        cup.roll()
        assertEquals(7, cup.value)

        cup.roll()
        assertEquals(11, cup.value)

    }
}