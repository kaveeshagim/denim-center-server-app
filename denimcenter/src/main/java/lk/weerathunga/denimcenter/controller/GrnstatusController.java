package lk.weerathunga.denimcenter.controller;

import lk.weerathunga.denimcenter.dao.GrnstatusDao;
import lk.weerathunga.denimcenter.entity.Grnstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/grnstatuses")
public class GrnstatusController {

    @Autowired
    private GrnstatusDao grnstatusDao;

    @GetMapping(path = "/list", produces = "application/json")
    public List<Grnstatus> get() {

        List<Grnstatus> grnstatuses = this.grnstatusDao.findAll();

        grnstatuses = grnstatuses.stream().map(
                grnstatus -> { Grnstatus d = new Grnstatus();
                    d.setId(grnstatus.getId());
                    d.setName(grnstatus.getName());
                    return d; }
        ).collect(Collectors.toList());

        return grnstatuses;
    }
}

