package cd.dgi.di.notifications;

import java.util.List;

record Tranche(
      int basse,
      int haute,
      int taux
) {}
public record TrancheImpot(
        String libelle,
        List<Tranche> tranches
) {}
