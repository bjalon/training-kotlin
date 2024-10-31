package eu.centralpay.ui

class ConsoleViewModel(val users: MutableList<Utilisateur> = ArrayList()) {
    fun addUtilisateur(utilisateur: Utilisateur) {
        if (users.any { it.id == utilisateur.id })
            println("User already exists")
        else
            users.add(utilisateur)
    }

    fun getUtilisateur(id: Int): Utilisateur? = users.firstOrNull { it.id == id }

    fun afficherTousLesUtilisateurs() {
        users.forEach { println(it) }
    }
}