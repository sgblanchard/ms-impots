package cd.dgi.di.profiles;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByLibelle(RoleLibelle roleLibelle);
}
