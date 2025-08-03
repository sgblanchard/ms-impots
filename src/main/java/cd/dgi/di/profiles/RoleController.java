package cd.dgi.di.profiles;

import jakarta.persistence.GeneratedValue;
import org.springframework.cglib.core.Block;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public void creer(@RequestBody Role role) {
        this.roleService.creer(role);
    }

    @GetMapping
    public List<Role> rechercher() {
        return this.roleService.rechercher();
    }
}
