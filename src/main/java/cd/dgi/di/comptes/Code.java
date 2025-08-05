package cd.dgi.di.comptes;

import cd.dgi.di.profiles.Profil;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.Random;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@Table(name = "codes")
public class Code {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int valeur;
    private Instant dateExpiration;
    @ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.DETACH
    })
    @JoinColumn(name = "profiles_id")
    private Profil profil;
    public Code() {
        Random random = new Random();
        this.valeur = 100000 + random.nextInt(900000);
        this.dateExpiration = Instant
                .now()
                .plusSeconds(600);
    }
    public boolean estValide() {
        return Instant.now().isBefore(dateExpiration);
    }
}
