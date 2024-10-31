package eu.centralpay.domain

import eu.centralpay.domain.exceptions.DiceNotRolledException
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

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
    fun `should dice balanced`() {
        val resultsCount = HashMap<Int, Int>()

        val rollCount = 600_000
        val facesCount = 6
        val expectedLimit = getExpectedLimit(rollCount, facesCount, 0.5)

        val dice = Dice(facesCount = facesCount)

        repeatRollExperience(dice, rollCount, resultsCount)

        resultsCount.forEach { (diceValue, valueCount) ->
            assertTrue(valueCount > expectedLimit, "$diceValue: $valueCount not enough than $expectedLimit")
        }
    }

    @Suppress("SameParameterValue")
    private fun repeatRollExperience(dice: Dice, rollCount: Int, resultsCount: HashMap<Int, Int>) {
        (1..rollCount).forEach {
            dice.roll()
            resultsCount[dice.value] = (resultsCount[dice.value] ?: 0) + 1
        }
        resultsCount.forEach { (diceValue, valueCount) ->
            println("$diceValue: $valueCount")
        }
    }

    @Suppress("SameParameterValue")
    private fun getExpectedLimit(rollCount: Int, faces: Int, errorExpected: Double): Double {
        val expectedLimit = rollCount / faces - rollCount * errorExpected / 100
        println("expectedLimit: $expectedLimit")
        return expectedLimit
    }
}