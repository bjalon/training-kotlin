package eu.centralpay.domain

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
            if (isUserHasWon(player)) {
                winner = player
                println("${player.name} has won the game!")
                break
            }
        }
    }

    fun playUntilAUserHasWon() {
        do {
            doRound()
        } while (isInProgress)
    }

    private fun isUserHasWon(player: Player) = player.currentCell.name == "Cell ${board.cellCount}"
}
