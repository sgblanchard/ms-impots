package cd.dgi.di.profiles;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProfilService {
    private ProfilRepository profilRepository;
    void creer(Profil profil) {
        this.profilRepository.save(profil);
    }
}
