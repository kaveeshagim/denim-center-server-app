package lk.weerathunga.denimcenter.report.dao;

import lk.weerathunga.denimcenter.report.entity.CountByProductionstatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CountByProductionstatusDao extends JpaRepository<CountByProductionstatus, Integer> {

    @Query(value = "SELECT NEW CountByProductionstatus(s.name, COUNT(o.number)) FROM Productionorder o, Productionstatus s WHERE o.productionstatus.id = s.id GROUP BY s.id")
    List<CountByProductionstatus> countByProductionstatus();

}
