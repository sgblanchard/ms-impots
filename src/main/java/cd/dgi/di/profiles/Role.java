package cd.dgi.di.profiles;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private RoleLibelle libelle;
    private String description;

    public Role() {
    }

    public Role(int id, String description, RoleLibelle libelle) {
        this.id = id;
        this.description = description;
        this.libelle = libelle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RoleLibelle getLibelle() {
        return libelle;
    }

    public void setLibelle(RoleLibelle libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
