package lk.weerathunga.denimcenter.dao;

import lk.weerathunga.denimcenter.entity.Paymentmethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentmethodDao extends JpaRepository<Paymentmethod, Integer> {
}
