package eu.centralpay.domain.cells

import eu.centralpay.domain.Cell
import eu.centralpay.domain.Cup
import eu.centralpay.domain.Player

abstract class EvilCell(name: String, next: Cell? = null): Cell(name, next) {
    override fun action(player: Player, cup: Cup) {
        player.lostOnePiece()
        println("\t$name: user lost one piece: ${player.pieceCount}")
    }
}
