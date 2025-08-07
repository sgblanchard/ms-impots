package cd.dgi.di.declarations;

import java.time.Instant;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "declarations")
public class Declaration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int annee;
    private Instant creation;
    private double impotTotal;
    private String statut;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinTable(
            name = "declarations_declarations_items",
            joinColumns = @JoinColumn( name = "declarations_id"),
            inverseJoinColumns = @JoinColumn(name = "declarations_items_id")
    )
    private List<DeclarationItem> items;
}
