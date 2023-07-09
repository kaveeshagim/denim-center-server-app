package lk.weerathunga.denimcenter.controller;

import lk.weerathunga.denimcenter.dao.PrivilegeDao;
import lk.weerathunga.denimcenter.entity.Privilege;
import lk.weerathunga.denimcenter.entity.Privilege;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping(value = "/privileges")
public class PrivilegeController {

    @Autowired
    private PrivilegeDao privilegedao;

    @GetMapping(produces = "application/json")
    public List<Privilege> get(@RequestParam HashMap<String, String> params) {

        String roleid = params.get("roleid");
        String moduleid = params.get("moduleid");



        List<Privilege> privileges = this.privilegedao.findAll();

        if(params.isEmpty()) return privileges;

        Stream<Privilege> estream = privileges.stream();

        if(roleid!=null) estream = estream.filter(e -> e.getRole().getId()==Integer.parseInt(roleid));
        if(moduleid!=null) estream = estream.filter(e -> e.getModule().getId()==Integer.parseInt(moduleid));



        return estream.collect(Collectors.toList());
    }


    @GetMapping(path = "/list", produces = "application/json")
    public List<Privilege> get() {

        List<Privilege> privileges = this.privilegedao.findAll();

        privileges = privileges.stream().map(
                privilege -> {Privilege s = new Privilege();
                    s.setId(privilege.getId());
                    s.setModule(privilege.getModule());
                    s.setName(privilege.getName());
                    s.setRole(privilege.getRole());
                    return s; }
        ).collect(Collectors.toList());

        return privileges;
    }


}
