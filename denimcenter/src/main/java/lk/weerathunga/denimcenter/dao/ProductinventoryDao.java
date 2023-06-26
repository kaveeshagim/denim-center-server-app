package lk.weerathunga.denimcenter.dao;

import lk.weerathunga.denimcenter.entity.Productinventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductinventoryDao extends JpaRepository<Productinventory, Integer> {

    Productinventory findByNumber(String number);

    Productinventory findAllById(Integer id);
}
