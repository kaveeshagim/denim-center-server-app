package lk.weerathunga.denimcenter.controller;

import lk.weerathunga.denimcenter.dao.GenderDao;
import lk.weerathunga.denimcenter.entity.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/genders")
public class GenderController {

    @Autowired
    private GenderDao genderDao;

    @GetMapping(path = "/list", produces = "application/json")
    public List<Gender> get() {
        // Retrieve all Gender objects from the database using the genderDao
        List<Gender> genders = this.genderDao.findAll();

        // Perform a mapping operation on each Gender object
        genders = genders.stream().map(gender -> {
            // Create a new Gender object
            Gender d = new Gender();
            // Set the id and name of the new Gender object to the corresponding values from the retrieved object
            d.setId(gender.getId());
            d.setName(gender.getName());
            // Return the new Gender object
            return d;
        }).collect(Collectors.toList());

        // Return the list of modified Gender objects
        return genders;
    }

}
