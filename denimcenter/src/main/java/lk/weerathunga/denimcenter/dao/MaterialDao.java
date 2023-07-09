package lk.weerathunga.denimcenter.dao;

import lk.weerathunga.denimcenter.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialDao extends JpaRepository<Material, Integer> {

    Material findByName(String name);
    Material findAllById(Integer id);
}
