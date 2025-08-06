package cd.dgi.di.declarations;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@AllArgsConstructor
@RestController
@RequestMapping(path = "declarations")
public class DeclarationsController {
    private DeclarationsService declarationsService;
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Object> rechercher() {
        return this.declarationsService.rechercher();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void creer(@RequestBody Declaration declaration) {
        this.declarationsService.creer(declaration);
    }
}
