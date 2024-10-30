package eu.centralpay.domain.cells

import eu.centralpay.domain.Cell
import eu.centralpay.domain.Cup
import eu.centralpay.domain.Player

class RedirectCell(name: String, next: Cell? = null): EvilCell(name, next) {

    lateinit var linkedCell: Cell

    override fun action(player: Player, cup: Cup) {
        super.action(player, cup)
        player.moveToCell(linkedCell, cup)
        println("\tBridge action move to ${linkedCell.name}")
    }
}
