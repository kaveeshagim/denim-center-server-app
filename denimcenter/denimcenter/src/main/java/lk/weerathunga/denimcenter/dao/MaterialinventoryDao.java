package lk.weerathunga.denimcenter.dao;

import lk.weerathunga.denimcenter.entity.Materialinventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialinventoryDao extends JpaRepository<Materialinventory, Integer> {

    Materialinventory findByNumber(String number);

    Materialinventory findAllById(Integer id);
}
