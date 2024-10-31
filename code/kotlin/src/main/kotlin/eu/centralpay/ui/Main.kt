package eu.centralpay.ui

fun main() {
    val activity = MyActivity()
    // Ajout d'utilisateurs
    activity.myViewModel.addUtilisateur(Utilisateur(1, "Alice", "alice@example.com"))
    activity.myViewModel.addUtilisateur(Utilisateur(2, "Bob", "bob@example.com"))
    // Affichage des utilisateurs
    println("Liste des utilisateurs :")
    activity.myViewModel.afficherTousLesUtilisateurs()
    // Essai de récupérer un utilisateur par ID
    val utilisateur = activity.myViewModel.getUtilisateur(1)
    println("Utilisateur récupéré : $utilisateur")
}