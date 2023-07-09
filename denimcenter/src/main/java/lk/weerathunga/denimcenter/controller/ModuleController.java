package lk.weerathunga.denimcenter.controller;


import lk.weerathunga.denimcenter.dao.ModuleDao;
import lk.weerathunga.denimcenter.entity.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/modules")
public class ModuleController {

    @Autowired
    private ModuleDao moduleDao;

    @GetMapping(path = "/list", produces = "application/json")
    public List<Module> get() {

        List<Module> modules = this.moduleDao.findAll();

        modules = modules.stream().map(
                module -> { Module d = new Module();
                    d.setId(module.getId());
                    d.setName(module.getName());
                    return d; }
        ).collect(Collectors.toList());

        return modules;
    }
}

