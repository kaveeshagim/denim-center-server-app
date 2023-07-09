package lk.weerathunga.denimcenter.dao;

import lk.weerathunga.denimcenter.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceDao extends JpaRepository<Invoice, Integer> {

    Invoice findByNumber(String number);
    Invoice findAllById(Integer id);
}
