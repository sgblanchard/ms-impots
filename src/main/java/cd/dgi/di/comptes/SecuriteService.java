package cd.dgi.di.comptes;

import cd.dgi.di.profiles.Profil;
import cd.dgi.di.profiles.ProfilRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class SecuriteService {
    ProfilRepository profilRepository;
    public Profil profilConnecte() {
        Jwt jwt = (Jwt) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        String email = jwt.getSubject();
        return
                profilRepository
                        .findByEmail(email)
                        .orElseThrow(() -> new RuntimeException("Erreur de connexion"));
    }
}