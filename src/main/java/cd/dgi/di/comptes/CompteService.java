package cd.dgi.di.comptes;

import cd.dgi.di.notifications.EmailService;
import cd.dgi.di.profiles.*;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class CompteService {
    private final CodeRepository codeRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ProfilService profilService;
    private final EmailService emailService;
    private final RoleService roleService;

    @Transactional
    public void inscription(Compte compte) {

        // role
        Role role = this.roleService.getByLibelle(RoleLibelle.CONTRIBUABLE);
        String encoded = this.passwordEncoder.encode(compte.motDePasse());
        Profil profil = Profil.builder()
                .motDePasse(encoded)
                .email(compte.email())
                .telephone(compte.telephone())
                .nom(compte.nom())
                .prenom(compte.prenom())
                .roles(Set.of(role))
                .build();
        profil = profilService.creer(profil);

        Code code = new  Code();
        code.setProfil(profil);

        Code codeEnregistre = codeRepository.save(code);
        this.emailService.send(profil, codeEnregistre);
    }

    @Transactional
    public void activation(Map<String, String> parametres) {
        int valeurCode = Integer.parseInt(parametres.get("code"));
        Optional<Code> optionalCodeByValeur = this
                .codeRepository.findByValeur(valeurCode);
        if(optionalCodeByValeur.isEmpty()) {}

        Code code = optionalCodeByValeur.get();
        if (!code.estValide()) {}

        Profil profil = this.profilService.findByEmail(
                code.getProfil().getEmail()
        );
        profil.setActif(true);

        code.setDateExpiration(Instant.now());
    }
}
