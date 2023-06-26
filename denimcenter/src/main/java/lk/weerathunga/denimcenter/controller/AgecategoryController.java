package lk.weerathunga.denimcenter.controller;

import lk.weerathunga.denimcenter.dao.AgecategoryDao;
import lk.weerathunga.denimcenter.entity.Agecategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/agecategories")
public class AgecategoryController {

    @Autowired
    private AgecategoryDao agecategoryDao;

    @GetMapping(path = "/list", produces = "application/json")
    public List<Agecategory> get() {

        List<Agecategory> agecategories = this.agecategoryDao.findAll();

        agecategories = agecategories.stream().map(
                agecategory -> { Agecategory d = new Agecategory();
                    d.setId(agecategory.getId());
                    d.setName(agecategory.getName());
                    return d; }
        ).collect(Collectors.toList());

        return agecategories;
    }
}


