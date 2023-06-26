package lk.weerathunga.denimcenter.dao;

import lk.weerathunga.denimcenter.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {

    User findByUsername(String username);
    User findByPassword(String password);

    User findAllById(Integer id);
}
