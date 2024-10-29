package eu.centralpay.domain

import eu.centralpay.domain.exceptions.DiceNotRolledException

class Dice {
    val facesCount: Int = 6

    var value: Int = -1
        get() = if (field == -1) throw DiceNotRolledException() else field
        private set


    fun roll() {
        value = (1..facesCount).random()
    }

}
