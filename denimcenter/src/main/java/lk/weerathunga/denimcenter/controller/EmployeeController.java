package lk.weerathunga.denimcenter.controller;

import lk.weerathunga.denimcenter.dao.EmployeeDao;
import lk.weerathunga.denimcenter.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

    @Autowired
    private EmployeeDao employeedao;

    @GetMapping(produces = "application/json")
    public List<Employee> get(@RequestParam HashMap<String, String> params) {

        String id = params.get("id");
        String genderid = params.get("genderid");
        String firstname = params.get("firstname");
        String lastname = params.get("lastname");
        String departmentid = params.get("departmentid");
        String nic = params.get("nic");

        List<Employee> employees = this.employeedao.findAll();

        if(params.isEmpty()) return employees;

        Stream<Employee> estream = employees.stream();

                    if(id!=null) estream = estream.filter(e -> e.getId() ==  Integer.parseInt(id));
                    if(genderid!=null) estream = estream.filter(e -> e.getGender().getId()==Integer.parseInt(genderid));
                    if(firstname!=null) estream = estream.filter(e -> e.getFirstname().contains(firstname));
                    if(lastname!=null) estream = estream.filter(e -> e.getLastname().contains(lastname));
                    if(departmentid!=null) estream = estream.filter(e -> e.getDepartment().getId()==Integer.parseInt(departmentid));
                    if(nic!=null) estream = estream.filter(e -> e.getNic().contains(nic));

                    return estream.collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String, String> add(@RequestBody Employee employee){

        HashMap<String, String> response = new HashMap<>();
        String errors="";

        if(employeedao.findByNic(employee.getNic())!=null)
            errors = errors+"<br> Existing Nic";

        if(errors=="")
            employeedao.save(employee);
        else errors = "Server Validation Errors : <br> "+errors;

        response.put("id", String.valueOf(employee.getId()));
        response.put("url","/employees/"+employee.getId());
        response.put("errors",errors);

        return response;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> update(@RequestBody Employee employee){

        HashMap<String, String> response = new HashMap<>();
        String errors="";

        Employee emp = employeedao.findByNic(employee.getNic());

        if(emp!=null && employee.getId()!=emp.getId())
            errors = errors+"<br> Existing NIC";

        if(errors=="") employeedao.save(employee);
        else errors = "Server Validation Errors : <br> "+errors;

        response.put("id", String.valueOf(employee.getId()));
        response.put("url","/employees/"+employee.getId());
        response.put("errors",errors);

        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> delete(@PathVariable Integer id){
        System.out.println(id);
        HashMap<String,String> response = new HashMap<>();
        String errors="";
        Employee emp = employeedao.findAllById(id);
        if(emp==null)
            errors = errors+"<br> employee Does Not Existed";
        if(errors=="") employeedao.delete(emp);
        else errors = "Server Validation Errors : <br> "+errors;
        response.put("id",String.valueOf(id));
        response.put("url","/employees/"+id);
        response.put("errors",errors);
        return response;
    }
}
