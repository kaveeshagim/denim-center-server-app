package lk.weerathunga.denimcenter.dao;

import lk.weerathunga.denimcenter.entity.Materialmovement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialmovementDao extends JpaRepository<Materialmovement, Integer> {
    Materialmovement findAllById(Integer id);
}
