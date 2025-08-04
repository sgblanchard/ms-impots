package cd.dgi.di.comptes;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CodeRepository extends JpaRepository<Code, Integer> {
    Optional<Code> findByValeur(int valeur);
}
