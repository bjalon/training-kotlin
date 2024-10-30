package eu.centralpay.domain.cells

import eu.centralpay.domain.Cell
import eu.centralpay.domain.Cup
import eu.centralpay.domain.Player

class JailCell(name: String, next: Cell? = null): EvilCell(name, next) {
    var frozenPlayer: Player? = null

    override fun action(player: Player, cup: Cup) {
        super.action(player, cup)
        frozenPlayer?.let {
            println("\tJail action unfreeze ${it.name}")
            it.unFreeze()
        }
        println("\tJail action freeze ${player.name}")
        frozenPlayer = player
        player.freeze()
    }
}
