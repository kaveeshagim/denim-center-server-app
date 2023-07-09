package lk.weerathunga.denimcenter.dao;

import lk.weerathunga.denimcenter.entity.Customer;
import lk.weerathunga.denimcenter.entity.Customerpayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerpaymentDao extends JpaRepository<Customerpayment, Integer> {

    Customerpayment findAllById(Integer id);
}
