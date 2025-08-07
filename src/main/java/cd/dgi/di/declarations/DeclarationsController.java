package cd.dgi.di.declarations;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@AllArgsConstructor
@RestController
@RequestMapping(path = "declarations")
public class DeclarationsController {
    private DeclarationsService declarationsService;
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<Declaration> rechercher() {
        return this.declarationsService.rechercher();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void creer(@RequestBody Declaration declaration) {
        this.declarationsService.creer(declaration);
    }
}
