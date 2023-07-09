package lk.weerathunga.denimcenter.dao;

import lk.weerathunga.denimcenter.entity.Porder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PorderDao extends JpaRepository<Porder, Integer> {

    Porder findByNumber(String number);
    Porder findAllById(Integer id);
}
