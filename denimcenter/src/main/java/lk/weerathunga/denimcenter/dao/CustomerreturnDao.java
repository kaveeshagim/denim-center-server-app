package lk.weerathunga.denimcenter.dao;

import lk.weerathunga.denimcenter.entity.Customerreturn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerreturnDao extends JpaRepository<Customerreturn, Integer> {

    Customerreturn findAllById(Integer id);
}
