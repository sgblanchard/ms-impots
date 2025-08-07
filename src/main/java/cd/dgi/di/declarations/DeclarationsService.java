package cd.dgi.di.declarations;

import cd.dgi.di.comptes.SecuriteService;
import cd.dgi.di.profiles.Profil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@AllArgsConstructor
@Service
public class DeclarationsService {
    private SecuriteService securiteService;
    private DeclarationRepository declarationRepository;
    public Set<Declaration> rechercher() {
        Profil profil = this.securiteService.profilConnecte();
        return profil.getDeclarations();
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
