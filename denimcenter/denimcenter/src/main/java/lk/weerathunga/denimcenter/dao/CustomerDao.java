package lk.weerathunga.denimcenter.dao;
import lk.weerathunga.denimcenter.entity.Customer;
import lk.weerathunga.denimcenter.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDao extends JpaRepository<Customer, Integer> {

    Customer findByNic(String nic);

    Customer findAllById(Integer id);
}
