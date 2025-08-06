package cd.dgi.di.comptes;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@AllArgsConstructor
@RestController
public class CompteController {
    private final JWTService jwtService;
    private final CompteService compteService;
    private AuthenticationManager authenticationManager;
    @PostMapping(path = "inscription")
    public void inscription(@RequestBody Compte compte) {
        this.compteService.inscription(compte);
    }
    @PostMapping(path = "activation")
    public void activation(@RequestBody Map<String, String> parametres) {
        this.compteService.activation(parametres);
    }
    @PostMapping(path = "connexion")
    public Map<String, String> connexion(@RequestBody Map<String, String> parametres) {
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        parametres.get("username"),
                        parametres.get("password")
                )
        );
       return this.jwtService.generateToken(authentication);
    }
}