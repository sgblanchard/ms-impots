package cd.dgi.di.profiles;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "profils")
public class Profil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String prenom;
    @Column(unique = true, nullable = false)
    private String email;
    private String telephone;
    private boolean actif = false;
    private String motDePasse;
}
