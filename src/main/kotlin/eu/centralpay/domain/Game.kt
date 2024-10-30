package eu.centralpay.domain

import eu.centralpay.domain.exceptions.UserWonException

class Game(playerNames: List<String>, val cup: Cup = Cup()) {

    val board = Board()
    val players = playerNames.map { Player(it, board.startCell) }
    var winner: Player? = null
        private set
    val isInProgress: Boolean
        get() = winner == null
    var roundIndex = 0

    fun doRound() {
        println("***************** Starting round ${++roundIndex} *****************")
        for (player in players) {
            player.play(cup)
        }
    }

    fun playUntilAUserHasWon() {
        try {
            while (true) {
                doRound()
            }
        } catch (e: UserWonException) {
            winner = e.player
            println("${e.player.name} has won the game!")
        }
    }

}
