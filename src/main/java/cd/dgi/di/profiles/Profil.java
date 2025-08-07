package cd.dgi.di.profiles;


import cd.dgi.di.declarations.Declaration;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "profils")
public class Profil implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String prenom;
    @Column(unique = true, nullable = false)
    private String email;
    private String telephone;
    private boolean actif = true;
    private String motDePasse;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinTable(
            name = "profils_roles",
            joinColumns = @JoinColumn( name = "profils_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id")
    )
    private Set<Role> roles;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinTable(
            name = "profils_declarations",
            joinColumns = @JoinColumn( name = "profils_id"),
            inverseJoinColumns = @JoinColumn(name = "declarations_id")
    )
    private Set<Declaration> declarations = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        this.roles.forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getLibelle()));
        });
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.motDePasse;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.actif;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.actif;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.actif;
    }

    @Override
    public boolean isEnabled() {
        return this.actif;
    }
}
