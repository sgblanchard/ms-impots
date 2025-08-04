package cd.dgi.di.comptes;

import cd.dgi.di.notifications.EmailService;
import cd.dgi.di.profiles.Profil;
import cd.dgi.di.profiles.ProfilService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CompteService {
    private final CodeRepository codeRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ProfilService profilService;
    private final EmailService emailService;

    @Transactional
    public void inscription(Compte compte) {
        String encoded = this.passwordEncoder.encode(compte.motDePasse());
        Code code = new  Code();
        code.setEmail(compte.email());

        Profil profil = Profil.builder()
                .motDePasse(encoded)
                .email(compte.email())
                .telephone(compte.telephone())
                .nom(compte.nom())
                .prenom(compte.prenom())
                .build();
        profil = profilService.creer(profil);

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

        Profil profil = this.profilService.findByEmail(code.getEmail());
        profil.setActif(true);

        code.setDateExpiration(Instant.now());
    }
}
