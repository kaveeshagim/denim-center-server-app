package lk.weerathunga.denimcenter.controller;

import lk.weerathunga.denimcenter.dao.EmpstatusDao;
import lk.weerathunga.denimcenter.entity.Empstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/empstatuses")
public class EmpstatusController {

    @Autowired
    private EmpstatusDao empstatusDao;

    @GetMapping(path = "/list", produces = "application/json")
    public List<Empstatus> get() {

        List<Empstatus> empstatuses = this.empstatusDao.findAll();

        empstatuses = empstatuses.stream().map(
                empstatus -> { Empstatus d = new Empstatus();
                    d.setId(empstatus.getId());
                    d.setName(empstatus.getName());
                    return d; }
        ).collect(Collectors.toList());

        return empstatuses;
    }
}
