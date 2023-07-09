package lk.weerathunga.denimcenter.dao;

import lk.weerathunga.denimcenter.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    User findByPassword(String password);
    Boolean existsByUsername(String username);
    User findAllById(Integer id);
}

