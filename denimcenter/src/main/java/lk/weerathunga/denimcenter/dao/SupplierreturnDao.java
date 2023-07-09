package lk.weerathunga.denimcenter.dao;

import lk.weerathunga.denimcenter.entity.Supplierreturn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierreturnDao extends JpaRepository<Supplierreturn, Integer> {

    Supplierreturn findAllById(Integer id);
}
