package lk.weerathunga.denimcenter.controller;

import lk.weerathunga.denimcenter.dao.OrderstatusDao;
import lk.weerathunga.denimcenter.entity.Orderstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/orderstatuses")
public class OrderstatusController {

    @Autowired
    private OrderstatusDao orderstatusDao;

    @GetMapping(path = "/list", produces = "application/json")
    public List<Orderstatus> get() {

        List<Orderstatus> orderstatuses = this.orderstatusDao.findAll();

        orderstatuses = orderstatuses.stream().map(
                orderstatus -> { Orderstatus d = new Orderstatus();
                    d.setId(orderstatus.getId());
                    d.setName(orderstatus.getName());
                    return d; }
        ).collect(Collectors.toList());

        return orderstatuses;
    }
}

