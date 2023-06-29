package lk.weerathunga.denimcenter.dao;
import lk.weerathunga.denimcenter.entity.Billofmaterial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillofmaterialDao extends JpaRepository<Billofmaterial, Integer> {

    Billofmaterial findAllById(Integer id);
}

