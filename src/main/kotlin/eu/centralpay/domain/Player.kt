package eu.centralpay.domain

class Player(val name: String, var currentCell: Cell) {
    var standbyRoundCount: Int = 0
        private set
    var pieceCount: Int = 20
        private set
    var isFrozen: Boolean = false

    fun play(cup: Cup) {
        if (standbyRoundCount > 0) {
            standbyRoundCount --
            println("${name}(${currentCell.name}) is on standby for $standbyRoundCount round(s)... Next player")
        } else if (isFrozen) {
            println("${name}(${currentCell.name}) is frozen on cell")
        } else {
            cup.roll()
            val moveValue = cup.value
            println("${name}(${currentCell.name}) has rolled $moveValue")
            moveToCell(moveValue, cup)
        }
    }

    fun moveToCell(moveValue: Int, cup: Cup) {
        val targetCell = (moveValue downTo 1).fold(currentCell) { acc, _ -> acc.next }
        moveToCell(targetCell = targetCell, cup = cup)
    }

    fun moveToCell(targetCell: Cell, cup: Cup) {
        currentCell = targetCell
        currentCell.action(player = this, cup = cup)
    }

    fun lostOnePiece() {
        pieceCount--
    }

    fun moveAgain(cup: Cup) {
        play(cup)
    }

    fun standbyOnNextRound(standbyRoundCount: Int = 1) {
        this.standbyRoundCount = standbyRoundCount
    }

    fun unFreeze() {
        isFrozen = false
    }

    fun freeze() {
        isFrozen = true
    }
}
