package lk.weerathunga.denimcenter.controller;

import lk.weerathunga.denimcenter.dao.UserstatusDao;
import lk.weerathunga.denimcenter.entity.Userstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/userstatuses")
public class UserstatusController {

    @Autowired
    private UserstatusDao userstatusDao;

    @GetMapping(path = "/list", produces = "application/json")
    public List<Userstatus> get() {

        List<Userstatus> userstatuses = this.userstatusDao.findAll();

        userstatuses = userstatuses.stream().map(
                userstatus -> { Userstatus d = new Userstatus();
                    d.setId(userstatus.getId());
                    d.setName(userstatus.getName());
                    return d; }
        ).collect(Collectors.toList());

        return userstatuses;
    }
}

