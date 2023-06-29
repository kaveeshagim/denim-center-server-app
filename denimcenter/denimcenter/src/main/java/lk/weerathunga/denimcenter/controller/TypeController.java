package lk.weerathunga.denimcenter.controller;

import lk.weerathunga.denimcenter.dao.TypeDao;
import lk.weerathunga.denimcenter.entity.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/types")
public class TypeController {

    @Autowired
    private TypeDao typeDao;

    @GetMapping(path = "/list", produces = "application/json")
    public List<Type> get() {

        List<Type> types = this.typeDao.findAll();

        types = types.stream().map(
                type -> { Type d = new Type();
                    d.setId(type.getId());
                    d.setName(type.getName());
                    return d; }
        ).collect(Collectors.toList());

        return types;
    }
}

