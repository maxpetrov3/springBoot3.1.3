package sb241.sb241.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sb241.sb241.model.Role;
import sb241.sb241.repositories.RoleRepository;

import java.util.List;


@RestController("/api/roles")
public class RestRoleController {

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/api/roles")
    public ResponseEntity<List> getAllRoles() {
        return new ResponseEntity<>(roleRepository.findAll(), HttpStatus.OK);
    }
}
