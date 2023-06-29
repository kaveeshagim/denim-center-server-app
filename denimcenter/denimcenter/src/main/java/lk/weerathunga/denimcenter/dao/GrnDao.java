package lk.weerathunga.denimcenter.dao;

import lk.weerathunga.denimcenter.entity.Grn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrnDao extends JpaRepository<Grn, Integer> {

    Grn findByNumber(String number);

    Grn findAllById(Integer id);
}
