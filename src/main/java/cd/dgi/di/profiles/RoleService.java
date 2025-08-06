package cd.dgi.di.profiles;

import cd.dgi.di.notifications.DataClient;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class RoleService {
    private final static Logger LOGGER = Logger.getLogger(RoleService.class.getName());
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final DataClient dataClient;
    private final RestClient restClient;

    public RoleService(RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder, DataClient dataClient, RestClient restClient) {
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.dataClient = dataClient;
        this.restClient = restClient;
    }

    public void creer(Role role) {

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

    public Role getByLibelle(RoleLibelle roleLibelle) {
        Optional<Role> optionalRole = this.roleRepository.findByLibelle(roleLibelle);
        if(optionalRole.isEmpty()) {
            //List<Role> maps = this.dataClient.roles();
            /*
            Role roleToSave = this.dataClient.roles()
                    .stream()
                    .filter(role -> role.getLibelle().equals(roleLibelle))
                    .findFirst()
                    .get();
*/
            return this.roleRepository.save(
                    Role.builder()
                            .libelle(RoleLibelle.CONTRIBUABLE)
                            //.description(roleToSave.getDescription())
                            .build()
            );

        } else {
            return optionalRole.get();
        }
    }

}
