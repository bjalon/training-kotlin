package eu.centralpay.domain

fun main() {
    val playerNames = listOf("Benjamin", "Fadoua", "Bénédicte", "Reece")
    val game = Game(playerNames = playerNames)
    game.playUntilAUserHasWon()
}