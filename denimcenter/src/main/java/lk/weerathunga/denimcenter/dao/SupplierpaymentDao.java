package lk.weerathunga.denimcenter.dao;

import lk.weerathunga.denimcenter.entity.Supplierpayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierpaymentDao extends JpaRepository<Supplierpayment, Integer> {
    Supplierpayment findAllById(Integer id);
}
