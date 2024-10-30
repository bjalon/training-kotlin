package eu.centralpay.domain.exceptions

import eu.centralpay.domain.Player

class UserWonException(val player: Player) : Throwable()
