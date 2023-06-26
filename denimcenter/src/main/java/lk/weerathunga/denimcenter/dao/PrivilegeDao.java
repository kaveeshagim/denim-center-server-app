package lk.weerathunga.denimcenter.dao;

import lk.weerathunga.denimcenter.entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeDao extends JpaRepository<Privilege, Integer> {
}
