package cd.dgi.di.comptes;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@AllArgsConstructor
@RestController
public class CompteController {
    private final CompteService compteService;
    @PostMapping(path = "inscription")
    public void inscription(@RequestBody Compte compte) {
        this.compteService.inscription(compte);
    }
}
