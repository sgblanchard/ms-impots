package cd.dgi.di.profiles;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class RoleService {
    private final static Logger LOGGER = Logger.getLogger(RoleService.class.getName());
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public RoleService(RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void creer(Role role) {

        System.out.println("Créer le role" + role.getDescription());
        LOGGER.log(Level.INFO, String.format("Créer le role %s", role.getDescription()));
        String descriptionEncode = this.passwordEncoder.encode(role.getDescription());
        role.setDescription(descriptionEncode);
        roleRepository.save(role);
    }

    public Role lire(int id) {
        return null;
    }
    public Role modifier(int id, Role role) {
        return null;
    }
    public void supprimer(int id) {

    }


    public List<Role> rechercher() {
       return this.roleRepository.findAll();
    }
}
