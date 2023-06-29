package lk.weerathunga.denimcenter.controller;
import lk.weerathunga.denimcenter.dao.RoleDao;
import lk.weerathunga.denimcenter.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/roles")
public class RoleController {

    @Autowired
    private RoleDao roleDao;

    @GetMapping(path = "/list", produces = "application/json")
    public List<Role> get() {

        List<Role> roles = this.roleDao.findAll();

        roles = roles.stream().map(
                role -> { Role d = new Role();
                    d.setId(role.getId());
                    d.setName(role.getName());
                    return d; }
        ).collect(Collectors.toList());

        return roles;
    }
}



