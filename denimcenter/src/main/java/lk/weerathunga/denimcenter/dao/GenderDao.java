package lk.weerathunga.denimcenter.dao;

import lk.weerathunga.denimcenter.entity.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderDao extends JpaRepository<Gender, Integer> {
}
