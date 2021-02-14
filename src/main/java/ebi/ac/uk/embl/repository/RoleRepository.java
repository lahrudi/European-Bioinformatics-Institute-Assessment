package ebi.ac.uk.embl.repository;

import ebi.ac.uk.embl.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Alireza Gholamzadeh Lahroodi on 2/12/2021.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(String name);
}
