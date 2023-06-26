package lk.weerathunga.denimcenter.report.dao;

import lk.weerathunga.denimcenter.report.entity.CountByPorderstatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CountByPorderstatusDao extends JpaRepository<CountByPorderstatus, Integer> {

    @Query(value = "SELECT NEW CountByPorderstatus(s.name, COUNT(o.number)) FROM Porder o, Orderstatus s WHERE o.orderstatus.id = s.id GROUP BY s.id")
    List<CountByPorderstatus> countByPorderstatus();

}
