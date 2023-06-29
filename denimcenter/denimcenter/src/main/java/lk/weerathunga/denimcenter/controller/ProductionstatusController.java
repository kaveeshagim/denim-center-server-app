package lk.weerathunga.denimcenter.controller;

import lk.weerathunga.denimcenter.dao.ProductionstatusDao;
import lk.weerathunga.denimcenter.entity.Productionstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/productionstatuses")
public class ProductionstatusController {

    @Autowired
    private ProductionstatusDao productionstatusDao;

    @GetMapping(path = "/list", produces = "application/json")
    public List<Productionstatus> get() {

        List<Productionstatus> productionstatuses = this.productionstatusDao.findAll();

        productionstatuses = productionstatuses.stream().map(
                productionstatus -> { Productionstatus d = new Productionstatus();
                    d.setId(productionstatus.getId());
                    d.setName(productionstatus.getName());
                    return d; }
        ).collect(Collectors.toList());

        return productionstatuses;
    }
}

