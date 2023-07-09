package lk.weerathunga.denimcenter.dao;

import lk.weerathunga.denimcenter.entity.Corder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorderDao extends JpaRepository<Corder, Integer> {

    Corder findByNumber(String number);
    Corder findAllById(Integer id);
}
