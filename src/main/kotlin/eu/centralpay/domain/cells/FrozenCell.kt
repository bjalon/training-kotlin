package eu.centralpay.domain.cells

import eu.centralpay.domain.Cell
import eu.centralpay.domain.Cup
import eu.centralpay.domain.Player

abstract class FrozenCell(name: String, private val frozenRoundCount: Int = 1, next: Cell? = null): EvilCell(name, next) {
    override fun action(player: Player, cup: Cup) {
        super.action(player, cup)
        player.standbyOnNextRound(standbyRoundCount = frozenRoundCount)
        println("\t$name: frozen during $frozenRoundCount round(s)")
    }
}

class HostelCell(name: String, next: Cell? = null): FrozenCell(name, 1, next)

class WellCell(name: String, next: Cell? = null): FrozenCell(name, 2, next)