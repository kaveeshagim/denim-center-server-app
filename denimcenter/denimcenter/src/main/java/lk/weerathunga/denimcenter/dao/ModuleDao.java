package lk.weerathunga.denimcenter.dao;

import lk.weerathunga.denimcenter.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleDao extends JpaRepository<Module, Integer> {
}
