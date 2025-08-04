package cd.dgi.di.profiles;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProfilService {
    private ProfilRepository profilRepository;
    public Profil creer(Profil profil) {
        return this.profilRepository.save(profil);
    }

    public Profil findByEmail(String email) {
        return profilRepository
                .findByEmail(email)
                .orElseThrow(() -> new RuntimeException(String.format("Email %s inconnu", email)));
    }
}
