package lk.weerathunga.denimcenter.dao;

import lk.weerathunga.denimcenter.entity.Productmovement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductmovementDao extends JpaRepository<Productmovement, Integer> {

    Productmovement findAllById(Integer id);
}
