package lk.weerathunga.denimcenter.dao;

import lk.weerathunga.denimcenter.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDao extends JpaRepository<Employee, Integer> {

    Employee findByNic(String nic);
    Employee findAllById(Integer id);
}
