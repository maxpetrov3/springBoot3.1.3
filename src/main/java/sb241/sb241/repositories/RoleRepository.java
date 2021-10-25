package sb241.sb241.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sb241.sb241.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
