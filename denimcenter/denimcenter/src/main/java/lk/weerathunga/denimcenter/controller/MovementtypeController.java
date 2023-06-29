package lk.weerathunga.denimcenter.controller;

import lk.weerathunga.denimcenter.dao.MovementtypeDao;
import lk.weerathunga.denimcenter.entity.Movementtype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/movementtypes")
public class MovementtypeController {

    @Autowired
    private MovementtypeDao movementtypeDao;

    @GetMapping(path = "/list", produces = "application/json")
    public List<Movementtype> get() {

        List<Movementtype> movementtypes = this.movementtypeDao.findAll();

        movementtypes = movementtypes.stream().map(
                movementtype -> { Movementtype d = new Movementtype();
                    d.setId(movementtype.getId());
                    d.setName(movementtype.getName());
                    return d; }
        ).collect(Collectors.toList());

        return movementtypes;
    }
}

