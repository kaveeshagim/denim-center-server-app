package lk.weerathunga.denimcenter.report.dao;

import lk.weerathunga.denimcenter.report.entity.CountByCorderstatus;
import lk.weerathunga.denimcenter.report.entity.CountByProductionstatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CountByCorderstatusDao extends JpaRepository<CountByCorderstatus, Integer> {

    @Query(value = "SELECT NEW CountByCorderstatus(s.name, COUNT(o.number)) FROM Corder o, Orderstatus s WHERE o.orderstatus.id = s.id GROUP BY s.id")
    List<CountByCorderstatus> countByCorderstatus();

}
