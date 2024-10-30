package eu.centralpay.domain.cells

import eu.centralpay.domain.Cell
import eu.centralpay.domain.Cup
import eu.centralpay.domain.Player

class GooseCell(name: String, next: Cell? = null): Cell(name, next) {
    override fun action(player: Player, cup: Cup) {
        println("\tGoose cell move again to ${cup.value}")
        player.moveAgain(cup)
    }
}
