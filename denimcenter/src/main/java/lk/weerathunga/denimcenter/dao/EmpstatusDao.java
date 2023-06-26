package lk.weerathunga.denimcenter.dao;

import lk.weerathunga.denimcenter.entity.Empstatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpstatusDao extends JpaRepository<Empstatus, Integer> {
}
