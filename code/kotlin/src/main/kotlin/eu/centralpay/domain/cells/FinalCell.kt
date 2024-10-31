package eu.centralpay.domain.cells

import eu.centralpay.domain.Cell
import eu.centralpay.domain.Cup
import eu.centralpay.domain.Player
import eu.centralpay.domain.exceptions.UserWonException

class FinalCell(name: String, next: Cell? = null): Cell(name, next) {
    override fun action(player: Player, cup: Cup) {
        println("\t$name WINNER !!")
        throw UserWonException(player)
    }
}
