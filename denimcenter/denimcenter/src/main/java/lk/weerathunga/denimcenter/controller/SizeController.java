package lk.weerathunga.denimcenter.controller;
import lk.weerathunga.denimcenter.dao.SizeDao;
import lk.weerathunga.denimcenter.entity.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/sizes")
public class SizeController {

    @Autowired
    private SizeDao sizeDao;

    @GetMapping(path = "/list", produces = "application/json")
    public List<Size> get() {

        List<Size> sizes = this.sizeDao.findAll();

        sizes = sizes.stream().map(
                size -> { Size d = new Size();
                    d.setId(size.getId());
                    d.setName(size.getName());
                    return d; }
        ).collect(Collectors.toList());

        return sizes;
    }
}



