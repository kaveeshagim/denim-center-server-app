package lk.weerathunga.denimcenter.dao;

import lk.weerathunga.denimcenter.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Integer> {
}
