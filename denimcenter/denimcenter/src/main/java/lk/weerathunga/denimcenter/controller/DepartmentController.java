package lk.weerathunga.denimcenter.controller;

import lk.weerathunga.denimcenter.dao.DepartmentDao;
import lk.weerathunga.denimcenter.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/departments")
public class DepartmentController {

    @Autowired
    private DepartmentDao departmentDao;

    @GetMapping(path = "/list", produces = "application/json")
    public List<Department> get() {

        List<Department> departments = this.departmentDao.findAll();

        departments = departments.stream().map(
                department -> { Department d = new Department();
                d.setId(department.getId());
                d.setName(department.getName());
                return d; }
        ).collect(Collectors.toList());

        return departments;
    }
}
