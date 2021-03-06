package sb241.sb241.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sb241.sb241.model.Role;
import sb241.sb241.repositories.RoleRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Set<Role> getAllRoles() {
        return roleRepository.findAll().stream().collect(Collectors.toSet());
    }

    public Role getRoleById(Long id) {
        return roleRepository.findById(id).get();
    }
}
