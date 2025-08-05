package cd.dgi.di.profiles;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

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

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinTable(
            name = "profils_roles",
            joinColumns = @JoinColumn( name = "profils_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id")
    )
    private Set<Role> roles;
}
