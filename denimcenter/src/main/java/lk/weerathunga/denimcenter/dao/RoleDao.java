package lk.weerathunga.denimcenter.dao;

import lk.weerathunga.denimcenter.entity.ERole;
import lk.weerathunga.denimcenter.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleDao extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);
}
