package lk.weerathunga.denimcenter.dao;

import lk.weerathunga.denimcenter.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorDao extends JpaRepository<Color, Integer> {
}
