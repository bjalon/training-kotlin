package eu.centralpay.domain

import eu.centralpay.domain.exceptions.CupNotRolledException
import eu.centralpay.domain.exceptions.DiceNotRolledException

class Cup(val dice: List<Dice> = listOf(Dice())) {

    val value: Int
        get() =
            try {
                dice.sumOf { it.value }
            } catch (e: DiceNotRolledException) {
                throw CupNotRolledException()
            }

    fun roll() {
        dice.forEach { it.roll() }
    }
}
