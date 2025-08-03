package cd.dgi.di.comptes;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class CompteService {
    private final BCryptPasswordEncoder passwordEncoder;
    public void inscription(Compte compte) {
        String encoded = this.passwordEncoder.encode(compte.motDePasse());

    }
}
