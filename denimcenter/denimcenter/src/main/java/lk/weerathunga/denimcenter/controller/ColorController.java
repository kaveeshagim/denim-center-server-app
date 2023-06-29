package lk.weerathunga.denimcenter.controller;

import lk.weerathunga.denimcenter.dao.ColorDao;
import lk.weerathunga.denimcenter.entity.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/colors")
public class ColorController {

    @Autowired
    private ColorDao colorDao;

    @GetMapping(path = "/list", produces = "application/json")
    public List<Color> get() {

        List<Color> colors = this.colorDao.findAll();

        colors = colors.stream().map(
                color -> { Color d = new Color();
                    d.setId(color.getId());
                    d.setName(color.getName());
                    return d; }
        ).collect(Collectors.toList());

        return colors;
    }
}

