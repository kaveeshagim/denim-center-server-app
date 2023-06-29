package lk.weerathunga.denimcenter.controller;

import lk.weerathunga.denimcenter.dao.SupplierDao;
import lk.weerathunga.denimcenter.entity.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping(value = "/suppliers")
public class SupplierController {

    @Autowired
    private SupplierDao supplierdao;

    @GetMapping(produces = "application/json")
    public List<Supplier> get(@RequestParam HashMap<String, String> params) {

        String id = params.get("id");
        String companyname = params.get("companyname");
        String name = params.get("name");
        String mobile = params.get("mobile");
        String nic = params.get("nic");

        List<Supplier> suppliers = this.supplierdao.findAll();

        if (params.isEmpty()) return suppliers;

        Stream<Supplier> estream = suppliers.stream();

        if (id != null) estream = estream.filter(e -> e.getId() == Integer.parseInt(id));
        if (mobile != null) estream = estream.filter(e -> e.getMobile().contains(mobile));
        if (companyname != null) estream = estream.filter(e -> e.getCompanyname().contains(companyname));
        if (name != null) estream = estream.filter(e -> e.getName().contains(name));
        if (nic != null) estream = estream.filter(e -> e.getNic().contains(nic));

        return estream.collect(Collectors.toList());

    }



    @GetMapping(path = "/list", produces = "application/json")
    public List<Supplier> get() {

        List<Supplier> suppliers = this.supplierdao.findAll();

        suppliers = suppliers.stream().map(
                supplier -> { Supplier s = new Supplier();
                    s.setId(supplier.getId());
                    s.setCompanyname(supplier.getCompanyname());
                    return s; }
        ).collect(Collectors.toList());

        return suppliers;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String, String> add(@RequestBody Supplier supplier){

        HashMap<String, String> response = new HashMap<>();
        String errors="";

        if(supplierdao.findByNic(supplier.getNic())!=null)
            errors = errors+"<br> Existing Nic";

        if(errors=="")
            supplierdao.save(supplier);
        else errors = "Server Validation Errors : <br> "+errors;

        response.put("id", String.valueOf(supplier.getId()));
        response.put("url","/suppliers/"+supplier.getId());
        response.put("errors",errors);

        return response;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> update(@RequestBody Supplier supplier){

        HashMap<String, String> response = new HashMap<>();
        String errors="";

        Supplier sup1 = supplierdao.findByNic(supplier.getNic());

        //System.out.println("ID-"+supplier.getId()+"-"+supplier.getNic());
        //if(emp1!=null) System.out.println("NIC-"+emp1.getNic());

        if(sup1!=null && supplier.getId()!=sup1.getId())
            errors = errors+"<br> Existing NIC";

        //System.out.println(supplier.getFirstname());
        //System.out.println("Err-"+errors);

        if(errors=="") supplierdao.save(supplier);
        else errors = "Server Validation Errors : <br> "+errors;

        response.put("id", String.valueOf(supplier.getId()));
        response.put("url","/suppliers/"+supplier.getId());
        response.put("errors",errors);

        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> delete(@PathVariable Integer id){
        System.out.println(id);
        HashMap<String,String> response = new HashMap<>();
        String errors="";
        Supplier emp1 = supplierdao.findAllById(id);
        if(emp1==null)
            errors = errors+"<br> supplier Does Not Existed";
        if(errors=="") supplierdao.delete(emp1);
        else errors = "Server Validation Errors : <br> "+errors;
        response.put("id",String.valueOf(id));
        response.put("url","/suppliers/"+id);
        response.put("errors",errors);
        return response;
    }
}
