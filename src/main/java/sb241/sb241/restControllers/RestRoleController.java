package sb241.sb241.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sb241.sb241.model.Role;
import sb241.sb241.repositories.RoleRepository;

import java.util.List;


@RestController
public class RestRoleController {

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/getAllRoles")
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
