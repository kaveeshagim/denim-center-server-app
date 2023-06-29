package lk.weerathunga.denimcenter.dao;

import lk.weerathunga.denimcenter.entity.Employee;
import lk.weerathunga.denimcenter.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierDao extends JpaRepository<Supplier, Integer> {

    Supplier findByNic(String nic);

    Supplier findAllById(Integer id);
}
