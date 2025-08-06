package cd.dgi.di.declarations;

import cd.dgi.di.comptes.SecuriteService;
import cd.dgi.di.profiles.Profil;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class DeclarationsService {
    private SecuriteService securiteService;
    private DeclarationRepository declarationRepository;
    public List<Object> rechercher() {
        return new ArrayList<>();
    }

    @Transactional
    public void creer(Declaration declaration) {
        Declaration declarationEnregistre = this.declarationRepository.save(declaration);
        Profil profil = this.securiteService.profilConnecte();
        if(profil.getDeclarations() == null) {
            profil.setDeclarations(
                    Set.of(declarationEnregistre)
            );
        } else {
            profil.getDeclarations().add(declarationEnregistre);
        }

    }
}
