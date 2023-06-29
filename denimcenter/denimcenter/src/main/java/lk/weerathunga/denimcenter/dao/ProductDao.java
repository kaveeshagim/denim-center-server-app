package lk.weerathunga.denimcenter.dao;

import lk.weerathunga.denimcenter.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDao extends JpaRepository<Product, Integer> {

    // Retrieves a product by its code
    Product findByCode(String code);

    // Retrieves a product by its ID
    Product findAllById(Integer id);
}
