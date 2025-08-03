package cd.dgi.di.comptes;

public record Compte(
        String nom,
        String prenom,
        String email,
        String telephone,
        String motDePasse
) {
}
