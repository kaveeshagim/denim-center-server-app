package lk.weerathunga.denimcenter.controller;

import lk.weerathunga.denimcenter.dao.CustomerDao;
import lk.weerathunga.denimcenter.entity.Customer;
import lk.weerathunga.denimcenter.entity.Customer;
import lk.weerathunga.denimcenter.entity.Customer;
import lk.weerathunga.denimcenter.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    @Autowired
    private CustomerDao customerdao;

    @GetMapping(produces = "application/json")
    public List<Customer> get(@RequestParam HashMap<String, String> params) {

        String id = params.get("id");
        String companyname = params.get("companyname");
        String name = params.get("name");
        String mobile = params.get("mobile");
        String nic = params.get("nic");

        List<Customer> customers = this.customerdao.findAll();

        if(params.isEmpty()) return customers;

        Stream<Customer> estream = customers.stream();

                    if(id!=null) estream = estream.filter(e -> e.getId() ==  Integer.parseInt(id));
                    if(mobile!=null) estream = estream.filter(e -> e.getMobile().contains(mobile));
                    if(companyname!=null) estream = estream.filter(e -> e.getCompanyname().contains(companyname));
                    if(name!=null) estream = estream.filter(e -> e.getName().contains(name));
                    if(nic!=null) estream = estream.filter(e -> e.getNic().contains(nic));

                    return estream.collect(Collectors.toList());
    }

    @GetMapping(path = "/list", produces = "application/json")
    public List<Customer> get() {

        List<Customer> customers = this.customerdao.findAll();

        customers = customers.stream().map(
                customer -> {Customer s = new Customer();
                    s.setId(customer.getId());
                    s.setCompanyname(customer.getCompanyname());
                    return s; }
        ).collect(Collectors.toList());

        return customers;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String, String> add(@RequestBody Customer customer){

        HashMap<String, String> response = new HashMap<>();
        String errors="";

        if(customerdao.findByNic(customer.getNic())!=null)
            errors = errors+"<br> Existing Nic";

        if(errors=="")
            customerdao.save(customer);
        else errors = "Server Validation Errors : <br> "+errors;

        response.put("id", String.valueOf(customer.getId()));
        response.put("url","/customers/"+customer.getId());
        response.put("errors",errors);

        return response;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> update(@RequestBody Customer customer){

        HashMap<String, String> response = new HashMap<>();
        String errors="";

        Customer cus1 = customerdao.findByNic(customer.getNic());

        //System.out.println("ID-"+customer.getId()+"-"+customer.getNic());
        //if(emp1!=null) System.out.println("NIC-"+emp1.getNic());

        if(cus1!=null && customer.getId()!=cus1.getId())
            errors = errors+"<br> Existing NIC";

        //System.out.println(customer.getFirstname());
        //System.out.println("Err-"+errors);

        if(errors=="") customerdao.save(customer);
        else errors = "Server Validation Errors : <br> "+errors;

        response.put("id", String.valueOf(customer.getId()));
        response.put("url","/customers/"+customer.getId());
        response.put("errors",errors);

        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> delete(@PathVariable Integer id){
        System.out.println(id);
        HashMap<String,String> response = new HashMap<>();
        String errors="";
        Customer emp1 = customerdao.findAllById(id);
        if(emp1==null)
            errors = errors+"<br> customer Does Not Existed";
        if(errors=="") customerdao.delete(emp1);
        else errors = "Server Validation Errors : <br> "+errors;
        response.put("id",String.valueOf(id));
        response.put("url","/customers/"+id);
        response.put("errors",errors);
        return response;
    }
}
