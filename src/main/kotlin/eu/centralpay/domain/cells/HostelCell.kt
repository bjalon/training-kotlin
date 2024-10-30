package eu.centralpay.domain.cells

import eu.centralpay.domain.Cell
import eu.centralpay.domain.Cup
import eu.centralpay.domain.Player

class HostelCell(name: String, next: Cell? = null): EvilCell(name, next) {
    override fun action(player: Player, cup: Cup) {
        super.action(player, cup)
        player.standbyOnNextRound()
    }
}
