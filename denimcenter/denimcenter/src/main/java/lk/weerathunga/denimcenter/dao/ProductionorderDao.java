package lk.weerathunga.denimcenter.dao;

import lk.weerathunga.denimcenter.entity.Productionorder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductionorderDao extends JpaRepository<Productionorder, Integer> {

    Productionorder findByNumber(String number);

    Productionorder findAllById(Integer id);
}
