package eu.centralpay.domain

import eu.centralpay.domain.exceptions.DiceNotRolledException
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class DiceTest {

    @Test
    fun `should create dice`() {
        val dice = Dice()
        assertNotNull(dice)
    }

    @Test
    fun `should return the spear result`() {
        val dice = Dice()
        assertThrows<DiceNotRolledException> { dice.value }
    }

    @Test
    fun `should return a roll result between 1 and 6`() {
        val dice = Dice()
        (1..100).forEach {
            dice.roll()
            assertTrue(dice.value in 1..6)
        }
    }

    @Test
    fun `should dice equilibrated`() {
        val resultsCount = HashMap<Int, Int>()

        val dice = Dice()

        val rollCount = 1000
        (1..rollCount).forEach {
            dice.roll()
            resultsCount[dice.value] = (resultsCount[dice.value] ?: 0) + 1
        }

        val faces = dice.facesCount

        resultsCount.forEach {
            assertTrue(it.value > rollCount / faces / 2)
        }
    }
}